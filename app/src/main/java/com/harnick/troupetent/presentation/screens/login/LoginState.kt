package com.harnick.troupetent.presentation.screens.login

data class LoginState(
	val error: String? = null,
	val savingToken: Boolean = false
)
