package uk.co.harnick.troupetent.features.onboarding.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingEvent

@Composable
fun TokenInput(
    onEvent: (OnboardingEvent) -> Unit
) {
    var input by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        Modifier.fillMaxWidth(fraction = 0.5f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = input,
            onValueChange = { input = it },
            Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = remember { { onEvent(OnboardingEvent.TokenSubmitted(input.text)) } }
        ) {
            Text("Login")
        }
    }
}
