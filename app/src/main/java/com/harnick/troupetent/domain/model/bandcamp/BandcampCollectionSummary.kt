package com.harnick.troupetent.domain.model.bandcamp

import com.harnick.troupetent.data.remote.bandcamp.dto.library_responses.collection_summary.CollectionSummaryEntity
import com.harnick.troupetent.data.remote.bandcamp.dto.library_responses.collection_summary.TralbumLookupEntity
import kotlinx.serialization.Serializable

@Serializable
data class BandcampCollectionSummary(
	val fanId: Long,
	val itemLookupList: Map<String, TralbumLookupEntity>,
	val username: String
)

fun CollectionSummaryEntity.toBandcampCollectionSummary(): BandcampCollectionSummary {
	return BandcampCollectionSummary(
		fanId,
		itemLookupList,
		fanUsername
	)
}