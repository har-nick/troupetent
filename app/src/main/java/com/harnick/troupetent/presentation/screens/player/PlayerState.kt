package com.harnick.troupetent.presentation.screens.player

import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItem
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItemTrack

data class PlayerState(
	val bandcampCollectionItem: BandcampCollectionItem,
	val bandcampCollectionItemTrackList: List<BandcampCollectionItemTrack>,
	val currentBandcampCollectionItemTrack: BandcampCollectionItemTrack = bandcampCollectionItemTrackList[0],
	val buffering: Boolean = true
)