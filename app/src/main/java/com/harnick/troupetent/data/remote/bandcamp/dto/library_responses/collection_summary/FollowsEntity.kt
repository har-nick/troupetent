package com.harnick.troupetent.data.remote.bandcamp.dto.library_responses.collection_summary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowsEntity(
	@SerialName("following")
	val followingEntity: FollowingEntity
)