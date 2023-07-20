package uk.co.harnick.troupetent.features.onboarding.presentation

import uk.co.harnick.troupetent.core.util.ResponseState

data class OnboardingState(
    val accountState: ResponseState<Unit>? = null,
    val tokenFieldIsVisible: Boolean = false,
    val webViewIsVisible: Boolean = false
)
