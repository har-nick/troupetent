package com.harnick.troupetent.data.remote.bandcamp.dto.library_responses.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemLookupEntity(
	@SerialName("item_type")
	val itemType: String,
	@SerialName("purchased")
	val isPurchased: Boolean
)