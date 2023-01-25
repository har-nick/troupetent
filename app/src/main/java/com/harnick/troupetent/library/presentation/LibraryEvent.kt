package com.harnick.troupetent.library.presentation

import com.harnick.troupetent.library.domain.bandcamp.BandcampCollectionItem

sealed class LibraryEvent {
	data class ItemSelected(val bandcampCollectionItem: BandcampCollectionItem) : LibraryEvent()
	data class NavigateToPlayer(val JSONEncodedItem: String, val JSONEncodedTrackList: String) :
		LibraryEvent()
	
	object NoInternet : LibraryEvent()
	
	object NoTrackList : LibraryEvent()
}
