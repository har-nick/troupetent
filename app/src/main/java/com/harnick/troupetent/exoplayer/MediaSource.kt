package com.harnick.troupetent.exoplayer

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItemTrack
import com.harnick.troupetent.exoplayer.AudioSourceStateEnums.*

// TODO: METADATA_KEY_ART w/ Coil

class MediaSource {
	private val onReadyListeners = mutableListOf<OnReadyListener>()
	
	private val isReady: Boolean
		get() = state == INITIALISED
	
	var audioMetadata: List<MediaMetadataCompat> = emptyList()
	
	private var state: AudioSourceStateEnums = CREATED
		set(value) {
			if (value == CREATED || value == ERROR) {
				synchronized(onReadyListeners) {
					field = value
					onReadyListeners.forEach { listener ->
						listener.invoke(isReady)
					}
				}
			} else {
				field = value
			}
		}
	
	fun whenReady(listener: OnReadyListener): Boolean {
		return if (state == CREATED || state == INITIALISED) {
			onReadyListeners += listener
			false
		} else {
			listener.invoke(isReady)
			true
		}
	}
	
	fun load(
		track: BandcampCollectionItemTrack
	) {
		state = INITIALISING
		
		audioMetadata = listOf(
			MediaMetadataCompat.Builder()
				.putString(
					MediaMetadataCompat.METADATA_KEY_MEDIA_ID,
					track.id.toString()
				)
				.putString(
					MediaMetadataCompat.METADATA_KEY_ARTIST,
					track.artist
				)
				.putString(
					MediaMetadataCompat.METADATA_KEY_DURATION,
					(track.duration * 1000).toString()
				)
				.putString(
					MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER,
					track.trackNumber.toString()
				)
				.build()
		)
		
		state = INITIALISED
	}
	
	fun asMediaSource(
		dataSource: CacheDataSource.Factory
	): ConcatenatingMediaSource {
		val concatenatingMediaSource = ConcatenatingMediaSource()
		
		audioMetadata.forEach { metadata ->
			val mediaItem = MediaItem.fromUri(
				MediaMetadataCompat.METADATA_KEY_MEDIA_URI
			)
			
			val mediaSource = ProgressiveMediaSource
				.Factory(dataSource)
				.createMediaSource(mediaItem)
			
			concatenatingMediaSource.addMediaSource(mediaSource)
		}
		
		return concatenatingMediaSource
	}
	
	fun asMediaItem() = audioMetadata.map { metadata ->
		val descrption = MediaDescriptionCompat.Builder()
			.setTitle(metadata.description.title)
			.setMediaId(metadata.description.mediaId)
			.setMediaUri(metadata.description.mediaUri)
			.build()
		
		MediaBrowserCompat.MediaItem(descrption, FLAG_PLAYABLE)
	}.toMutableList()
	
	fun refresh() {
		onReadyListeners.clear()
		state = CREATED
	}
}
typealias OnReadyListener = (Boolean) -> Unit