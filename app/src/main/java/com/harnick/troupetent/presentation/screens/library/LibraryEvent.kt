package com.harnick.troupetent.presentation.screens.library

import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItem

sealed class LibraryEvent {
	data class ItemSelected(val bandcampCollectionItem: BandcampCollectionItem) : LibraryEvent()
	data class NavigateToPlayer(val JSONEncodedItem: String, val JSONEncodedTrackList: String) :
		LibraryEvent()
	
	object NoInternet : LibraryEvent()
	
	object NoTrackList : LibraryEvent()
}
