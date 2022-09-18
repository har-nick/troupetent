package com.harnick.troupetent.exoplayer

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.ui.PlayerNotificationManager.NotificationListener
import com.harnick.troupetent.R
import com.harnick.troupetent.common.util.AppConstants

internal class NotificationManager(
	context: Context,
	sessionToken: MediaSessionCompat.Token,
	notificationListener: NotificationListener
) {
	inner class DescriptionAdapter(
		private val controller: MediaControllerCompat
	) : PlayerNotificationManager.MediaDescriptionAdapter {
		override fun getCurrentContentTitle(player: Player): CharSequence =
			controller.metadata.description.title.toString()
		
		override fun createCurrentContentIntent(player: Player): PendingIntent =
			controller.sessionActivity
		
		override fun getCurrentContentText(player: Player): CharSequence? =
			controller.metadata.description.subtitle

//		TODO: ART ICON
		
		override fun getCurrentLargeIcon(
			player: Player,
			callback: PlayerNotificationManager.BitmapCallback
		): Bitmap? = null
	}
	
	private val notificationManager: PlayerNotificationManager
	
	init {
		val mediaController = MediaControllerCompat(context, sessionToken)
		
		val builder = PlayerNotificationManager.Builder(
			context,
			AppConstants.MediaPlayer.Notification.PLAYBACK_NOTIFICATION_ID,
			AppConstants.MediaPlayer.Notification.PLAYBACK_NOTIFICATION_CHANNEL_ID
		)
		
		with(builder) {
			setMediaDescriptionAdapter(DescriptionAdapter(mediaController))
			setNotificationListener(notificationListener)
			setChannelNameResourceId(0)
			setChannelDescriptionResourceId(0)
		}
		
		notificationManager = builder.build()
		
		with(notificationManager) {
			setMediaSessionToken(sessionToken)
			setSmallIcon(R.drawable.ic_launcher_foreground)
			setUseRewindAction(false)
			setUseFastForwardAction(false)
		}
	}
	
	fun hideNotification() {
		notificationManager.setPlayer(null)
	}
	
	fun showNotification(player: Player) {
		notificationManager.setPlayer(player)
	}
}