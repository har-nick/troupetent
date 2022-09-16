package com.harnick.troupetent.data.remote.bandcamp.dto.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemArtEntity(
	@SerialName("art_id")
	val artId: Long,
	@SerialName("thumb_url")
	val thumbnailUrl: String,
	@SerialName("url")
	val coverUrl: String
)