package com.harnick.troupetent.domain.model.bandcamp

import com.harnick.troupetent.data.remote.bandcamp.dto.library_responses.collection_items.BandcampCollectionItemsResponseEntity
import kotlinx.serialization.Serializable

@Serializable
data class BandcampLibraryData(
	val downloadUrlsList: Map<String, String>,
	val bandcampCollectionItemList: List<BandcampCollectionItem>,
	val itemBandcampCollectionItemTrackLists: Map<String, List<BandcampCollectionItemTrack>>
)

fun BandcampCollectionItemsResponseEntity.toBandcampLibraryData(): BandcampLibraryData {
	return BandcampLibraryData(
		downloadUrlsList,
		itemList.map { itemEntity -> itemEntity.toBandcampCollectionItem() }
			.sortedWith(compareBy<BandcampCollectionItem, String>(String.CASE_INSENSITIVE_ORDER) { it.bandName }.thenBy { it.itemTitle }),
		itemTrackLists
			.mapKeys { entry ->
				entry.key.drop(1)                                                                           // Remove package type from keys for easier processing
			}
			.mapValues { entry ->
				entry.value.map { it.toTrack() }
			}
	)
}