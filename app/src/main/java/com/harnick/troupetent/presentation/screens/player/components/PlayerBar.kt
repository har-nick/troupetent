package com.harnick.troupetent.presentation.screens.player.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlayerBar(
	buffering: Boolean
) {
	Row(
		Modifier
			.fillMaxWidth()
			.height(80.dp),
		Arrangement.SpaceAround,
		Alignment.CenterVertically
	) {
		
		if (buffering) {
			CircularProgressIndicator(
				Modifier.size(50.dp),
				MaterialTheme.colorScheme.onSurface
			)
		} else {
		
		}
		
	}
}