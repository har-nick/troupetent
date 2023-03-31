package com.harnick.troupetent.authentication.presentation

actual sealed interface LoginEvent {
    object CaptchaServed : LoginEvent
    data class LoginError(val error: String) : LoginEvent
    object LoginFormSubmission : LoginEvent
    object RetryLogin : LoginEvent
    object UserDataSaved : LoginEvent
    data class WebViewPageLoaded(val rawCookies: String) : LoginEvent
    object WebViewPageLoading : LoginEvent
}
