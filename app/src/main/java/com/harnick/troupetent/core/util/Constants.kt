package com.harnick.troupetent.core.util

object AppConstants {
	object MediaPlayer {
		const val MEDIA_ROOT_ID = "tt_root_id"
		
		object Notification {
			const val PLAYBACK_NOTIFICATION_CHANNEL_ID = "Troupetent Player"
			const val PLAYBACK_NOTIFICATION_ID = 3
		}
	}
}

object BandcampConstants {
	const val BASE_URL = "https://bandcamp.com"
	const val SUMMARY_URL = "$BASE_URL/api/fan/2/collection_summary"
	const val ITEMS_URL = "$BASE_URL/api/fancollection/1/collection_items"
	const val ART_URL = "https://f4.bcbits.com/img"
}