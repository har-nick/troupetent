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
fun OnboardingButton(
	openDefaultLinks: () -> Unit,
	text: String
) {
	Button(
		openDefaultLinks,
		Modifier.width(200.dp),
		shape = MaterialTheme.shapes.medium,
		colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
	) {
		Text(
			text,
			color = MaterialTheme.colorScheme.onPrimary
		)
	}
}