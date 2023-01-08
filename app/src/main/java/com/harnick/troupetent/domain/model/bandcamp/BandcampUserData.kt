package com.harnick.troupetent.domain.model.bandcamp

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class BandcampUserData(
	val profilePictureId: Long,
	@PrimaryKey
	val username: String,
	val userToken: String
)
