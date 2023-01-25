package com.harnick.troupetent.player

import com.harnick.troupetent.library.domain.bandcamp.BandcampCollectionItem
import com.harnick.troupetent.library.domain.bandcamp.BandcampCollectionItemTrack

data class PlayerState(
	val bandcampCollectionItem: BandcampCollectionItem,
	val bandcampCollectionItemTrackList: List<BandcampCollectionItemTrack>,
	val currentBandcampCollectionItemTrack: BandcampCollectionItemTrack = bandcampCollectionItemTrackList[0],
	val buffering: Boolean = true
)