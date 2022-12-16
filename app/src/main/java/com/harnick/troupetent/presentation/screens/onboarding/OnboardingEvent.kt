package com.harnick.troupetent.presentation.screens.onboarding

sealed class OnboardingEvent {
	object OpenDefaultLinks: OnboardingEvent()
	object OpenWebView: OnboardingEvent()
}
