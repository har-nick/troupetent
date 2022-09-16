package com.harnick.troupetent.data.remote.bandcamp.dto.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BandcampCollectionItemsResponseEntity(
	@SerialName("collectors")
	val collectorsEntity: CollectorsEntity,
	@SerialName("item_lookup")
	val itemLookupList: Map<Long, ItemLookupEntity>,
	@SerialName("items")
	val itemList: List<BandcampCollectionItemEntity>,
	@SerialName("last_token")
	val lastToken: String,
	@SerialName("more_available")
	val moreItemsAvailable: Boolean,
	@SerialName("purchase_infos")
	val purchaseInfosEntity: PurchaseInfosEntity,
	@SerialName("redownload_urls")
	val downloadUrlsList: Map<String, String>,
	@SerialName("tracklists")
	val itemTrackLists: Map<String, List<TrackEntity>>
)