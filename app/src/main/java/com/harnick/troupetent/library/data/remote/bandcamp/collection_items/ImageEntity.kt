package com.harnick.troupetent.library.data.remote.bandcamp.collection_items

import kotlinx.serialization.Serializable

@Serializable
data class ImageEntity(
	val height: Int,
	val image_id: Int,
	val width: Int
)