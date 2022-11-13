package com.harnick.troupetent.presentation.screens.library.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QueueMusic
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun MainHeader(
	profilePictureId: Long?,
	greeting: String?,
	username: String?
) {
	Row(
		Modifier
			.clickable(false) {}
			.shadow(
				10.dp, MaterialTheme.shapes.medium
			)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colorScheme.primaryContainer)
			.fillMaxWidth()
			.statusBarsPadding()
			.padding(10.dp)
			.height(60.dp), Arrangement.SpaceBetween) {
		Row(
			Modifier.fillMaxHeight(), Arrangement.Start, Alignment.CenterVertically
		) {
			profilePictureId?.also { MainHeaderProfilePicture(profilePictureId) }
			
			Spacer(Modifier.width(15.dp))
			
			if (!username.isNullOrEmpty() && !greeting.isNullOrEmpty()) {
				MainHeaderGreeting(greeting, username)
			}
		}
		Row(
			Modifier.fillMaxHeight(), Arrangement.SpaceAround, Alignment.CenterVertically
		) {
			MainHeaderIconButton(
				Icons.Rounded.QueueMusic, "Open Queue"
			)
			MainHeaderIconButton(
				Icons.Rounded.Settings, "Open Settings"
			)
		}
	}
}