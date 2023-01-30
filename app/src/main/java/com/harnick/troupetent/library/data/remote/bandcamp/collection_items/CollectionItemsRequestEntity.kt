package com.harnick.troupetent.library.data.remote.bandcamp.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionItemsRequestEntity(
	@SerialName("fan_id")
	val fanId: Long,
	@SerialName("older_than_token")
	val olderThanToken: String,
	@SerialName("count")
	val itemCount: Long
)
