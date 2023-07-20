package uk.co.harnick.troupetent.features.onboarding.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingEvent

@Composable
fun LoginButtons(onEvent: (OnboardingEvent) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            enabled = false,
            onClick = remember { { onEvent(OnboardingEvent.OpenWebView) } }
        ) {
            Text("Login with WebView")
        }

        Button(
            onClick = remember { { onEvent(OnboardingEvent.ShowTokenField) } }
        ) {
            Text("Login with token")
        }
    }
}
