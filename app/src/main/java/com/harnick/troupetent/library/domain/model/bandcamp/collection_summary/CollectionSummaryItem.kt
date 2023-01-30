package com.harnick.troupetent.library.domain.model.bandcamp.collection_summary

import com.harnick.troupetent.library.data.remote.bandcamp.collection_summary.TralbumLookupItemEntity
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSummaryItem(
	val itemId: Long
)

fun TralbumLookupItemEntity.toCollectionSummaryItem(): CollectionSummaryItem {
	return CollectionSummaryItem(itemId)
}