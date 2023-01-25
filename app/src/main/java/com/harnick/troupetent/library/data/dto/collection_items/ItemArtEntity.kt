package com.harnick.troupetent.library.data.dto.collection_items

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