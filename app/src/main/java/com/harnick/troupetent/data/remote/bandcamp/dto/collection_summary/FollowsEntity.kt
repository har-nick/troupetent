package com.harnick.troupetent.data.remote.bandcamp.dto.collection_summary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowsEntity(
	@SerialName("following")
	val followingEntity: FollowingEntity
)