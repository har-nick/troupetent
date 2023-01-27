package com.harnick.troupetent.authentification.presentation.screens.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harnick.troupetent.authentification.presentation.screens.login.LoginEvent
import com.harnick.troupetent.authentification.presentation.screens.onboarding.components.SquaredButton


@Composable
fun LoginStateNotifier(
	status: String?,
	error: String?,
	handleEvent: (LoginEvent) -> Unit
) {
	Column(
		Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		if (status != null) {
			CircularProgressIndicator()
		}
		
		Box(Modifier.padding(20.dp)) {
			if (status != null || error != null) {
				Text((status ?: error)!!)
			}
		}
		
		if (error != null) {
			SquaredButton({ handleEvent(LoginEvent.RetryLogin) }, minWidth = 0.dp, "Retry Login")
		}
	}
}