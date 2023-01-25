package com.harnick.troupetent.library.presentation.components.header

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainHeaderGreeting(
	greeting: String,
	username: String
) {
	Text(
		"$greeting\n$username!",
		Modifier,
		MaterialTheme.colorScheme.onPrimaryContainer,
		style = MaterialTheme.typography.titleMedium
	)
}