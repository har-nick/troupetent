package com.harnick.troupetent.presentation.screens.player

import kotlinx.serialization.Serializable

@Serializable
data class PlayerNavArgs(
	val JSONEncodedItem: String,
	val JSONEncodedTrackList: String
)
