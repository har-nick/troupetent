package uk.co.harnick.troupetent.features.onboarding.presentation

sealed interface OnboardingEvent {
    object NavigateToLibrary : OnboardingEvent
//    object OpenWebView : OnboardingEvent
    object ShowTokenField : OnboardingEvent
    data class TokenSubmitted(val token: String) : OnboardingEvent
}
