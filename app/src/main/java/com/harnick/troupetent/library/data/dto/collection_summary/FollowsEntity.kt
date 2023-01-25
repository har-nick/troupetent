package com.harnick.troupetent.library.data.dto.collection_summary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowsEntity(
	@SerialName("following")
	val followingEntity: FollowingEntity
)