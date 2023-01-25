package com.harnick.troupetent.authentification.presentation.screens.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

) : ViewModel() {
	var state by mutableStateOf(OnboardingState())
		private set
	
	private val _uiEvent = Channel<OnboardingEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()
	
	private fun sendEvent(event: OnboardingEvent) {
		viewModelScope.launch {
			_uiEvent.send(event)
		}
	}
	
	fun onEvent(event: OnboardingEvent) {
		sendEvent(event)
	}
}