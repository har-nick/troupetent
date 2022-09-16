package com.harnick.troupetent.presentation.screens.login

import android.content.Context

sealed class LoginEvent {
	object OpenDefaultLinks : LoginEvent()
	object OpenWebView : LoginEvent()
	data class TokenFound(val context: Context, val token: String) : LoginEvent()
	object TokenSaved : LoginEvent()
}
