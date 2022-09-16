package com.harnick.troupetent.presentation.screens.player.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PlayerDisplayText(text: String, textWeight: FontWeight, textStyle: TextStyle) {
	Text(
		text,
		Modifier.fillMaxWidth(),
		MaterialTheme.colorScheme.onSurface,
		fontWeight = textWeight,
		maxLines = 1,
		style = textStyle
	)
}