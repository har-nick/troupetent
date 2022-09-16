package com.harnick.troupetent.presentation.screens.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnick.troupetent.common.util.Resource
import com.harnick.troupetent.domain.use_cases.collated.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	@ApplicationContext private val appContext: Context,
	private val loginUseCases: LoginUseCases
) : ViewModel() {
	var state by mutableStateOf(LoginState())
		private set

	private val _uiEvent = Channel<LoginEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()


	private fun sendEvent(event: LoginEvent) {
		viewModelScope.launch {
			_uiEvent.send(event)
		}
	}

	fun onEvent(event: LoginEvent) {
		when (event) {
			LoginEvent.OpenDefaultLinks -> {
				state = state.copy(
					canOpenLinks = true
				)
			}
			LoginEvent.OpenWebView -> {
				state = state.copy(
					webViewEnabled = true
				)
			}
			is LoginEvent.TokenFound -> {
				saveLoginToken(event.token)
			}
			is LoginEvent.TokenSaved -> {}
		}

		sendEvent(event)
	}

	private fun saveLoginToken(token: String) {
		loginUseCases.saveBandcampLoginTokenUseCase(
			File(appContext.dataDir, "bandcamp_token_enc"),
			token
		).onEach { emission ->
			when (emission) {
				is Resource.Saving -> {
					state = state.copy(
						savingToken = true
					)
				}
				is Resource.Error -> {
					state = state.copy(
						error = state.error.plus("\n${emission.message}")
					)
				}
				is Resource.Success -> {
					sendEvent(LoginEvent.TokenSaved)
				}
				else -> {}
			}
		}.launchIn(viewModelScope)
	}
}