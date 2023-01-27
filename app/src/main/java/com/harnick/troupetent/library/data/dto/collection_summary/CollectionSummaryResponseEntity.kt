package com.harnick.troupetent.library.data.dto.collection_summary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSummaryResponseEntity(
	@SerialName("collection_summary")
	val bandcampCollectionSummaryEntity: BandcampCollectionSummaryEntity,
	@SerialName("fan_id")
	val fanId: Long
)