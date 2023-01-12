package com.harnick.troupetent.data.remote.bandcamp.dto.library_responses.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionItemsRequestEntity(
	@SerialName("fan_id")
	val fanId: Long,
	@SerialName("older_than_token")
	val olderThanToken: String,
	@SerialName("count")
	val itemCount: Int
)