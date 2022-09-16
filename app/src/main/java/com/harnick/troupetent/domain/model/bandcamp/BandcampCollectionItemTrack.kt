package com.harnick.troupetent.domain.model.bandcamp

import com.harnick.troupetent.data.remote.bandcamp.dto.collection_items.TrackEntity
import kotlinx.serialization.Serializable

@Serializable
data class BandcampCollectionItemTrack(
	val artist: String,
	val duration: Double,
	val id: Long,
	val streamUrl: String,
	val title: String,
	val trackNumber: Int? = 0
)

fun TrackEntity.toTrack(): BandcampCollectionItemTrack {
	return BandcampCollectionItemTrack(
		artist,
		duration,
		id,
		streamUrl.values.first(),                                                                       // API returns stream url as Map<String, String>, despite only having one value
		title,
		trackNumber
	)
}