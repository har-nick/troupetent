package com.harnick.troupetent.library.domain.model.bandcamp.collection_summary

import com.harnick.troupetent.library.data.remote.bandcamp.collection_summary.BandcampCollectionSummaryEntity
import kotlinx.serialization.Serializable

@Serializable
data class BandcampCollectionSummary(
	val fanId: Long,
	val itemLookupList: List<CollectionSummaryItem>,
	val username: String
)

fun BandcampCollectionSummaryEntity.toBandcampCollectionSummary(): BandcampCollectionSummary {
	return BandcampCollectionSummary(
		fanId,
		itemLookupList.values.toList().map { lookupItem ->
			lookupItem.toCollectionSummaryItem()
		},
		fanUsername
	)
}