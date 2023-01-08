package com.harnick.troupetent.data.remote.bandcamp.dto.library_responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseEntity(
	@SerialName("error")
	val errorExists: Boolean,
	@SerialName("error_message")
	val errorMessage: String
)