package com.harnick.troupetent.presentation.screens.player.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FastForward
import androidx.compose.material.icons.rounded.FastRewind
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harnick.troupetent.presentation.screens.components.CustomIconButton

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
		CustomIconButton(
			Icons.Rounded.FastRewind,
			"Rewind Song",
			Modifier.size(50.dp)
		) {

		}

		if (buffering) {
			CircularProgressIndicator(
				Modifier.size(50.dp),
				MaterialTheme.colorScheme.onSurface
			)
		} else {
			CustomIconButton(
				Icons.Rounded.PlayArrow,
				"Play Song",
				Modifier.size(50.dp)
			) {

			}
		}

		CustomIconButton(
			Icons.Rounded.FastForward,
			"Skip Song",
			Modifier.size(50.dp)
		) {

		}
	}
}