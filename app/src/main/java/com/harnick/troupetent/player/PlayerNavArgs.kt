package com.harnick.troupetent.player

import kotlinx.serialization.Serializable

@Serializable
data class PlayerNavArgs(
	val JSONEncodedItem: String,
	val JSONEncodedTrackList: String
)
