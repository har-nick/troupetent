package com.harnick.troupetent.presentation.screens.login.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingButton(onClick: () -> Unit, text: String, activator: Boolean) {
	Button(
		onClick,
		Modifier.width(200.dp),
		enabled = activator,
		shape = MaterialTheme.shapes.medium,
		colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
	) {
		Text(
			text,
			color = MaterialTheme.colorScheme.onPrimary
		)
	}
}