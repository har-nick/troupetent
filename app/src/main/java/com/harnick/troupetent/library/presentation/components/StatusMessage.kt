package com.harnick.troupetent.library.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun StatusMessage(
	message: String
) {
	Text(
		message, Modifier.padding(10.dp, 0.dp), textAlign = TextAlign.Center
	)
}