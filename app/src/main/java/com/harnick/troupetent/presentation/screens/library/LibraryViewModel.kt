package com.harnick.troupetent.presentation.screens.library

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnick.troupetent.common.util.Resource.*
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionSummary
import com.harnick.troupetent.domain.use_cases.collated.LibraryUseCases
import com.harnick.troupetent.presentation.screens.library.LibraryEvent.ItemSelected
import com.harnick.troupetent.presentation.screens.library.LibraryEvent.NavigateToPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
	private val libraryUseCases: LibraryUseCases
) : ViewModel() {
	var state by mutableStateOf(
		LibraryState(
			headerGreeting = generateGreeting()
		)
	)
		private set
	
	private val _uiEvent = Channel<LibraryEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()
	
	init {
		loadSummary()
	}
	
	private fun sendEvent(event: LibraryEvent) {
		viewModelScope.launch {
			_uiEvent.send(event)
		}
	}
	
	fun onEvent(event: LibraryEvent) {
		when (event) {
			is ItemSelected -> {
				val trackList =
					state.bandcampLibraryData?.itemBandcampCollectionItemTrackLists?.get(event.bandcampCollectionItem.itemId.toString())
				
				if (trackList.isNullOrEmpty()) {
					state = state.copy(
						snackbarText = "Tracklist couldn't be found. Try resyncing?.",
					)
					showSnackbar()
				} else {
					sendEvent(
						NavigateToPlayer(
							Json.encodeToString(event.bandcampCollectionItem),
							Json.encodeToString(trackList)
						)
					)
				}
			}
			else -> {}
		}
	}
	
	private fun generateGreeting(): String {
		val currentTime = LocalDateTime.now().hour
		val greetings = mutableListOf(
			"Hello",
			"Hi",
			"Hey"
		)
		
		if ((5..11).contains(currentTime)) {
			greetings.add("Good morning")
		} else if ((12..16).contains(currentTime)) {
			greetings.add("Good afternoon")
		} else if ((17..20).contains(currentTime)) {
			greetings.add("Good evening")
		} else {
			greetings.add("Good night")
		}
		
		return greetings.random()
	}
	
	private fun showSnackbar() {
		viewModelScope.launch {
			state.snackbarState.showSnackbar(state.snackbarText!!)
		}
	}
	
	private fun loadSummary() {
		libraryUseCases.getBandcampCollectionSummaryUseCase().onEach { emission ->
			when (emission) {
				is Fetching -> {
					state = state.copy(
						statusMessage = "Fetching summary..."
					)
				}
				is Loading -> {
					state = state.copy(
						snackbarText = "No internet available - Using cache.",
						statusMessage = "Loading cached summary..."
					)
					showSnackbar()
				}
				is Success -> {
					loadUserData(emission.data!!.username)
					loadLibraryData(emission.data)
				}
				is Error -> {
					state = state.copy(
						errorMessage = emission.message,
						statusMessage = null
					)
				}
				else -> {}
			}
		}.launchIn(viewModelScope)
	}
	
	private fun loadLibraryData(summary: BandcampCollectionSummary) {
		libraryUseCases.getBandcampCollectionItemsUseCase(summary).onEach { emission ->
			when (emission) {
				is Loading -> {
					state = state.copy(
						statusMessage = "Loading library data..."
					)
				}
				is Fetching -> {
					state = state.copy(
						statusMessage = "Syncing library data..."
					)
				}
				is Success -> {
					state = state.copy(
						bandcampLibraryData = emission.data,
						statusMessage = null
					)
				}
				is Error -> {
					state = state.copy(
						errorMessage = emission.message,
						statusMessage = null
					)
				}
				else -> {}
			}
		}.launchIn(viewModelScope)
	}
	
	private fun loadUserData(username: String) {
		libraryUseCases.getBandcampUserDataUseCase(username).onEach { emission ->
			if (emission is Success) {
				state = state.copy(
					username = emission.data!!.username,
					userDataLoading = false,
					userProfilePictureId = emission.data.profilePictureId
				)
			}
		}.launchIn(viewModelScope)
	}
}