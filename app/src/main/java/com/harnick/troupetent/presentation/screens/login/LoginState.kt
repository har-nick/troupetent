package com.harnick.troupetent.presentation.screens.login

data class LoginState(
	val error: String? = null,
	val status: String? = null,
	val webViewEnabled: Boolean = true,
	val webViewVisible: Boolean = false
)
