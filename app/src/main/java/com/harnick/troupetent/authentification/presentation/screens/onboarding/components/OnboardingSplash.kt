package com.harnick.troupetent.authentification.presentation.screens.onboarding.components

import android.os.Build
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.harnick.troupetent.authentification.presentation.screens.onboarding.OnboardingEvent

@Composable
fun OnboardingSplash(
	handleEvent: (OnboardingEvent) -> Unit
) {
	if (Build.VERSION.SDK_INT > 30) {
		Text(
			"Before logging in, make sure Troupetent can open verification links.",
			Modifier.padding(15.dp, 0.dp),
			textAlign = TextAlign.Center,
			style = MaterialTheme.typography.bodyLarge
		)
		
		HyperlinkText(
			Modifier.padding(0.dp, 10.dp, 0.dp, 15.dp),
			textContent = "\"Why do I need to do this?\"",
			linkUrl = "https://github.com/har-nick/troupetent/wiki/FAQ#whats-all-this-verification-links-stuff",
			fontSize = MaterialTheme.typography.bodyLarge.fontSize
		)
		
		SquaredButton({ handleEvent(OnboardingEvent.OpenDefaultLinks) }, 200.dp, "Open Supported Links")
		
		Spacer(Modifier.height(5.dp))
	}
	
	SquaredButton({ handleEvent(OnboardingEvent.OpenWebView) }, 200.dp, "Login to Bandcamp")
}