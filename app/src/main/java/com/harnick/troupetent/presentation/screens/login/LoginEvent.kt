package com.harnick.troupetent.presentation.screens.login

import android.content.Context

sealed interface LoginEvent {
	object CaptchaServed : LoginEvent
	data class LoginError(val error: String) : LoginEvent
	object LoginFormSubmission : LoginEvent
	object RetryLogin : LoginEvent
	data class TokenFound(val context: Context, val token: String) : LoginEvent
	object TokenSaved : LoginEvent
	object WebViewPageLoaded : LoginEvent
	object WebViewPageLoading : LoginEvent
}
