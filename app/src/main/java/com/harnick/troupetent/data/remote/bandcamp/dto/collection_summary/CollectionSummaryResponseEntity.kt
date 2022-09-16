package com.harnick.troupetent.data.remote.bandcamp.dto.collection_summary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSummaryResponseEntity(
	@SerialName("collection_summary")
	val collectionSummaryEntity: CollectionSummaryEntity,
	@SerialName("fan_id")
	val fanId: Long
)