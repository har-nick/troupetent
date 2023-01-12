package com.harnick.troupetent.presentation.screens.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnick.troupetent.common.util.Resource
import com.harnick.troupetent.domain.use_cases.collated.LoginUseCases
import com.harnick.troupetent.presentation.screens.login.LoginEvent.*
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
			is CaptchaServed -> {
				if (!state.webViewVisible) {
					state = state.copy(
						webViewEnabled = true,
						webViewVisible = true
					)
				}
			}
			
			is LoginError -> {
				
				
				state = state.copy(
					error = event.error,
					status = null,
					webViewEnabled = false,
					webViewVisible = true
				)
			}
			
			is LoginFormSubmission -> {
				state = state.copy(
					status = "Logging in...",
					webViewEnabled = true,
					webViewVisible = false
				)
			}
			
			is RetryLogin -> {
				state = state.copy(
					error = null,
					status = null,
					webViewEnabled = true,
					webViewVisible = true
				)
			}
			
			is TokenFound -> {
				state = state.copy(
					status = "Saving token...",
					webViewEnabled = false,
					webViewVisible = false
				)
			}
			
			is TokenSaved -> {
				sendEvent(event)
			}
			
			is WebViewPageLoaded -> {
				state = state.copy(
					webViewVisible = true
				)
			}
			
			is WebViewPageLoading -> {
				state = state.copy(
					webViewVisible = false
				)
			}
		}
	}
	
	fun parseRawBandcampCookies(rawToken: String, localContext: Context) {
		val parsedCookies = rawToken
			.split(";")
			.associate { rawCookie ->
				val (name, value) = rawCookie.split("=")
				name to value
			}
			.mapKeys { splitCookie -> splitCookie.key.trim() }
		
		if (parsedCookies.containsKey("identity")) {
			val token = parsedCookies.getValue("identity")
			onEvent(TokenFound(localContext, token))
			saveLoginToken(token)
		}
	}
	
	private fun saveLoginToken(token: String) {
		loginUseCases.saveBandcampLoginTokenUseCase(
			File(appContext.dataDir, "bandcamp_token_enc"),
			token
		).onEach { emission ->
			if (emission is Resource.Error) {
				state = state.copy(
					error = emission.message
				)
			} else if (emission is Resource.Success) {
				sendEvent(TokenSaved)
			}
		}.launchIn(viewModelScope)
	}
}