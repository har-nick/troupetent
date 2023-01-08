package com.harnick.troupetent.data.remote.bandcamp.dto.library_responses.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackEntity(
	val artist: String,
	val duration: Double,
	@SerialName("file")
	val streamUrl: Map<String, String>,
	val id: Long,
	val title: String,
	@SerialName("track_number")
	val trackNumber: Int?
)