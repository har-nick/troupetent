package com.harnick.troupetent.library.data.dto.collection_summary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BandcampCollectionSummaryEntity(
	@SerialName("fan_id")
	val fanId: Long,
	@SerialName("follows")
	val followsEntity: FollowsEntity,
	@SerialName("tralbum_lookup")
	val itemLookupList: Map<String, TralbumLookupEntity>,
	@SerialName("url")
	val fanUrl: String,
	@SerialName("username")
	val fanUsername: String
)