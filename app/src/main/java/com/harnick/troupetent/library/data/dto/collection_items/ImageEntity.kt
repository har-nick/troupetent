package com.harnick.troupetent.library.data.dto.collection_items

import kotlinx.serialization.Serializable

@Serializable
data class ImageEntity(
	val height: Int,
	val image_id: Int,
	val width: Int
)