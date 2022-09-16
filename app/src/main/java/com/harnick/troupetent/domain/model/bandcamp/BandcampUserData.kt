package com.harnick.troupetent.domain.model.bandcamp

import kotlinx.serialization.Serializable

@Serializable
data class BandcampUserData(
	val username: String,
	val profilePictureId: Long
)
