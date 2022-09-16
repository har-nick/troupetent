package com.harnick.troupetent.data.remote.bandcamp.dto.collection_summary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSummaryEntity(
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