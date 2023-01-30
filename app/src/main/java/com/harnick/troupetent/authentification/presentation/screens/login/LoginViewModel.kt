package com.harnick.troupetent.authentification.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnick.troupetent.authentification.presentation.screens.login.LoginEvent.*
import com.harnick.troupetent.core.user_data.domain.use_cases.SaveBandcampUserDataUseCase
import com.harnick.troupetent.core.util.Resource.Error
import com.harnick.troupetent.core.util.Resource.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val saveBandcampUserDataUseCase: SaveBandcampUserDataUseCase
) : ViewModel() {
	private val _state = MutableStateFlow(LoginState())
	val state = _state.asStateFlow()
	
	private val _uiEvent = Channel<LoginEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()
	
	private fun sendEvent(event: LoginEvent) {
		viewModelScope.launch {
			_uiEvent.send(event)
		}
	}
	
	fun onEvent(event: LoginEvent) {
		when (event) {
			is CaptchaServed -> {
				if (!state.value.webViewVisible) {
					_state.value = _state.value.copy(
						webViewEnabled = true,
						webViewVisible = true
					)
				}
			}
			
			is LoginError -> {
				_state.value = _state.value.copy(
					error = event.error,
					status = null,
					webViewEnabled = false,
					webViewVisible = true
				)
			}
			
			is LoginFormSubmission -> {
				_state.value = _state.value.copy(
					status = "Logging in...",
					webViewEnabled = true,
					webViewVisible = false
				)
			}
			
			is RetryLogin -> {
				_state.value = _state.value.copy(
					error = null,
					status = null,
					webViewEnabled = true,
					webViewVisible = true
				)
			}
			
			is UserDataSaved -> {
				sendEvent(event)
			}
			
			is WebViewPageLoaded -> {
				checkForTokenCookie(event.rawCookies)
			}
			
			is WebViewPageLoading -> {
				_state.value = _state.value.copy(
					webViewVisible = false
				)
			}
		}
	}
	
	private fun checkForTokenCookie(rawCookies: String) {
		val parsedCookies = rawCookies
			.split(";")
			.associate { rawCookie ->
				val (name, value) = rawCookie.split("=")
				name to value
			}
			.mapKeys { splitCookie -> splitCookie.key.trim() }
		
		val token = parsedCookies
			.withDefault { null }
			.getValue("identity")
		
		if (token.isNullOrEmpty()) {
			_state.value = _state.value.copy(
				webViewVisible = true
			)
		} else if (!saveUserData(token).isActive) {
			saveUserData(token).start()
		}
	}
	
	private fun saveUserData(token: String) = viewModelScope.launch(Dispatchers.IO) {
		saveBandcampUserDataUseCase(token)
			.onEach { emission ->
				if (emission is Error) {
					_state.value = _state.value.copy(
						error = emission.message
					)
				} else if (emission is Success) {
					onEvent(UserDataSaved)
				} else if (!emission.message.isNullOrEmpty()) {
					_state.value = _state.value.copy(
						status = emission.message
					)
				}
			}
			.collect()
	}
}
