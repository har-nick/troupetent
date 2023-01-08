package com.harnick.troupetent.presentation.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SquaredButton(
	onClick: () -> Unit,
	minWidth: Dp = 0.dp,
	text: String
) {
	Button(
		onClick,
		Modifier
			.padding(10.dp, 0.dp)
			.defaultMinSize(minWidth, 0.dp),
		shape = MaterialTheme.shapes.medium,
		colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
	) {
		Text(
			text,
			color = MaterialTheme.colorScheme.onPrimary
		)
	}
}