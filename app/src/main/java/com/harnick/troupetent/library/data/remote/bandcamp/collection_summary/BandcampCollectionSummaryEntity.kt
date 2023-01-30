package com.harnick.troupetent.library.data.remote.bandcamp.collection_summary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BandcampCollectionSummaryEntity(
	@SerialName("fan_id")
	val fanId: Long,
	@SerialName("follows")
	val followsEntity: FollowsEntity,
	@SerialName("tralbum_lookup")
	val itemLookupList: Map<String, TralbumLookupItemEntity>,
	@SerialName("url")
	val fanUrl: String,
	@SerialName("username")
	val fanUsername: String
)