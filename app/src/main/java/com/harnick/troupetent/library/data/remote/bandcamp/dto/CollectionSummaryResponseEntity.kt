package com.harnick.troupetent.library.data.remote.bandcamp.dto

import com.harnick.troupetent.library.data.remote.bandcamp.collection_summary.BandcampCollectionSummaryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSummaryResponseEntity(
	@SerialName("collection_summary")
	val bandcampCollectionSummaryEntity: BandcampCollectionSummaryEntity,
	@SerialName("fan_id")
	val fanId: Long
)