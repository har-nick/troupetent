package com.harnick.troupetent.presentation.screens.player

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItemTrack
import com.harnick.troupetent.domain.use_cases.collated.PlayerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.internal.toImmutableList
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
	private val playerUseCases: PlayerUseCases,
	private val savedStateHandle: SavedStateHandle
) : ViewModel() {
	var state by mutableStateOf(
		PlayerState(
			bandcampCollectionItem = Json.decodeFromString(savedStateHandle["JSONEncodedItem"]!!),
			bandcampCollectionItemTrackList = Json.decodeFromString(savedStateHandle["JSONEncodedTrackList"]!!)
		)
	)
		private set

	private val _uiEvent = Channel<PlayerEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()

	init {
		createStreamableTrackList()
	}

	private fun createStreamableTrackList() {
		val rawTrackList = state.bandcampCollectionItemTrackList
		val streamableBandcampCollectionItemTrackList = mutableListOf<BandcampCollectionItemTrack>()

		viewModelScope.launch {
			rawTrackList.forEach { rawTrack ->
				launch {
					val newTrackUrl = playerUseCases.getBandcampStreamUrl(rawTrack.streamUrl)

					streamableBandcampCollectionItemTrackList.add(
						rawTrackList.indexOf(rawTrack),
						rawTrack.copy(streamUrl = newTrackUrl)
					)
				}.join()

				state = state.copy(
					bandcampCollectionItemTrackList = streamableBandcampCollectionItemTrackList.toImmutableList(),
					buffering = false
				)
			}
		}
	}


	private fun sendEvent(event: PlayerEvent) {
		viewModelScope.launch {
			_uiEvent.send(event)
		}
	}

	fun onEvent(event: PlayerEvent) {
		when (event) {

			else -> {}
		}
	}
}