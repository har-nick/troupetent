package com.harnick.troupetent.authentification.presentation.screens.onboarding

sealed class OnboardingEvent {
	object OpenDefaultLinks: OnboardingEvent()
	object OpenWebView: OnboardingEvent()
}
