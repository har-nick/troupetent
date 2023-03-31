package com.harnick.troupetent.onboarding.presentation

sealed class OnboardingEvent {
    object OpenDefaultLinks : OnboardingEvent()
    object OpenWebView : OnboardingEvent()
}
