package uk.co.harnick.troupetent.features.onboarding.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingEvent

@Composable
fun LoginPrompt(
    tokenFieldIsVisible: Boolean,
    onEvent: (OnboardingEvent) -> Unit
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (tokenFieldIsVisible) TokenInput(onEvent) else LoginButtons(onEvent)
    }
}
