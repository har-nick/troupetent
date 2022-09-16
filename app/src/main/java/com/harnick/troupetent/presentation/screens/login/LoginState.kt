package com.harnick.troupetent.presentation.screens.login

data class LoginState(
	val canOpenLinks: Boolean = false,
	val error: String? = null,
	val savingToken: Boolean = false,
	val webViewEnabled: Boolean = false
)
