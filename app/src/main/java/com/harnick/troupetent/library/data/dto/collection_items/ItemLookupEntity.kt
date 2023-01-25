package com.harnick.troupetent.library.data.dto.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemLookupEntity(
	@SerialName("item_type")
	val itemType: String,
	@SerialName("purchased")
	val isPurchased: Boolean
)