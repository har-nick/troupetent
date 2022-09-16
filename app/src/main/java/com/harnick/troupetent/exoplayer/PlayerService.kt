package com.harnick.troupetent.exoplayer

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.harnick.troupetent.common.util.AppConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerService : MediaBrowserServiceCompat() {
	companion object {
		private const val TAG = "MediaPlayerService"

		var currentDuration: Long = 0
			private set
	}

	inner class PlayerNotificationListener : PlayerNotificationManager.NotificationListener {
		override fun onNotificationCancelled(
			notificationId: Int,
			dismissedByUser: Boolean
		) {
			super.onNotificationCancelled(notificationId, dismissedByUser)

			stopForeground(true)
			isForegroundService = false
			stopSelf()
		}

		override fun onNotificationPosted(
			notificationId: Int,
			notification: Notification,
			ongoing: Boolean
		) {
			super.onNotificationPosted(notificationId, notification, ongoing)

			if (ongoing && !isForegroundService) {
				ContextCompat.startForegroundService(
					applicationContext,
					Intent(
						applicationContext,
						this@PlayerService.javaClass
					)
				)
				startForeground(
					notificationId,
					notification
				)
				isForegroundService = true
			}
		}
	}

	private inner class PlayerEventListener : Player.Listener {
		override fun onEvents(player: Player, events: Player.Events) {
			super.onEvents(player, events)

			currentDuration = player.duration
		}

		override fun onPlaybackStateChanged(playbackState: Int) {
			super.onPlaybackStateChanged(playbackState)

			when (playbackState) {
				Player.STATE_BUFFERING,
				Player.STATE_READY -> {
					mediaPlayerNotificationManager.showNotification(exoPlayer)
				}
				else -> {
					mediaPlayerNotificationManager.hideNotification()
				}
			}

		}

		override fun onPlayerError(error: PlaybackException) {
			super.onPlayerError(error)

			var message = "Troupetent failed somehow. Sorry!"

			if (error.errorCode == PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS) {
				message = "Couldn't fetch song!"
			}

			Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
		}
	}

	inner class AudioMediaPlayBackPreparer : MediaSessionConnector.PlaybackPreparer {
		override fun onCommand(
			player: Player,
			command: String,
			extras: Bundle?,
			cb: ResultReceiver?
		): Boolean {
			return false
		}

		override fun getSupportedPrepareActions(): Long =
			PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID or PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID

		override fun onPrepare(playWhenReady: Boolean) = Unit

		override fun onPrepareFromMediaId(
			mediaId: String,
			playWhenReady: Boolean,
			extras: Bundle?
		) {
			mediaSource.whenReady {
				val itemToPlay = mediaSource.audioMetadata.find {
					it.description.mediaId == mediaId
				}

				currentPlayingMedia = itemToPlay

				preparePlayer(
					mediaSource.audioMetadata,
					itemToPlay,
					playWhenReady
				)
			}
		}

		override fun onPrepareFromSearch(
			query: String,
			playWhenReady: Boolean,
			extras: Bundle?
		) = Unit

		override fun onPrepareFromUri(
			uri: Uri,
			playWhenReady: Boolean,
			extras: Bundle?
		) = Unit
	}

	inner class MediaQueueNavigator(
		mediaSessionCompat: MediaSessionCompat
	) : TimelineQueueNavigator(mediaSessionCompat) {
		override fun getMediaDescription(
			player: Player,
			windowIndex: Int
		): MediaDescriptionCompat {
			if (windowIndex < mediaSource.audioMetadata.size) {
				return mediaSource.audioMetadata[windowIndex].description
			}

			return MediaDescriptionCompat.Builder().build()
		}
	}

	@Inject
	lateinit var dataSourceFactory: CacheDataSource.Factory

	@Inject
	lateinit var exoPlayer: ExoPlayer

	@Inject
	lateinit var mediaSource: MediaSource

	private val serviceJob = SupervisorJob()
	private val serviceScope = CoroutineScope(
		Dispatchers.Main + serviceJob
	)

	private fun preparePlayer(
		mediaMetadata: List<MediaMetadataCompat>,
		itemToPlay: MediaMetadataCompat?,
		playWhenReady: Boolean
	) {
		val indexToPlay = if (currentPlayingMedia == null) 0
		else mediaMetadata.indexOf(itemToPlay)

		exoPlayer.addListener(PlayerEventListener())
		exoPlayer.setMediaSource(mediaSource.asMediaSource(dataSourceFactory))
		exoPlayer.prepare()
		exoPlayer.seekTo(indexToPlay, 0)
		exoPlayer.playWhenReady = playWhenReady
	}

	private lateinit var mediaSession: MediaSessionCompat

	private lateinit var mediaSessionConnector: MediaSessionConnector

	private lateinit var mediaPlayerNotificationManager: NotificationManager

	private var currentPlayingMedia: MediaMetadataCompat? = null

	private val isPlayerInitialised = false

	var isForegroundService: Boolean = false

	override fun onCreate() {
		super.onCreate()

		val sessionActivityIntent = packageManager
			?.getLaunchIntentForPackage(packageName)
			?.let { sessionIntent ->
				PendingIntent.getActivity(
					this,
					0,
					sessionIntent,
					PendingIntent.FLAG_UPDATE_CURRENT or
									PendingIntent.FLAG_IMMUTABLE
				)
			}

		mediaSession = MediaSessionCompat(this, TAG).apply {
			setSessionActivity(sessionActivityIntent)
			isActive = true
		}

		sessionToken = mediaSession.sessionToken

		mediaPlayerNotificationManager = NotificationManager(
			this,
			mediaSession.sessionToken,
			PlayerNotificationListener()
		)

//		serviceScope.launch {
//			mediaSource.load()
//		}

		mediaSessionConnector = MediaSessionConnector(mediaSession).apply {
			setPlaybackPreparer(AudioMediaPlayBackPreparer())
			setQueueNavigator(MediaQueueNavigator(mediaSession))
			setPlayer(exoPlayer)
		}

		mediaPlayerNotificationManager.showNotification(exoPlayer)
	}

	override fun onGetRoot(
		clientPackageName: String,
		clientUid: Int,
		rootHints: Bundle?
	): BrowserRoot? {
		return BrowserRoot(
			AppConstants.MediaPlayer.MEDIA_ROOT_ID,
			null
		)
	}

	override fun onLoadChildren(
		parentId: String,
		result: Result<MutableList<MediaBrowserCompat.MediaItem>>
	) {
		when (parentId) {
			AppConstants.MediaPlayer.MEDIA_ROOT_ID -> {
				val resultSent = mediaSource.whenReady { isInitialised ->
					if (isInitialised) {
						result.sendResult(mediaSource.asMediaItem())
					} else {
						result.sendResult(null)
					}
				}

				if (!resultSent) {
					result.detach()
				}
			}
			else -> Unit
		}
	}

	override fun onCustomAction(
		action: String,
		extras: Bundle?, result:
		Result<Bundle>
	) {
		super.onCustomAction(action, extras, result)

		when (action) {

		}
	}
}