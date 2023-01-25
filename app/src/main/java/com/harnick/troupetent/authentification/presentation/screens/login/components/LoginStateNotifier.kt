package com.harnick.troupetent.authentification.presentation.screens.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.harnick.troupetent.authentification.presentation.screens.onboarding.components.SquaredButton


@Composable
fun LoginStateNotifier(
	status: String,
	inProgress: Boolean = true,
	isError: Boolean = false,
	retryLoginEvent: () -> Unit
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		if (inProgress) {
			CircularProgressIndicator()
		}
		
		Box(
			modifier = Modifier.padding(20.dp)
		) {
			Text(
				text = status,
				textAlign = TextAlign.Center
			)
		}
		
		if (isError) {
			SquaredButton(onClick = { retryLoginEvent() }, minWidth = 0.dp, text = "Retry Login")
		}
	}
}