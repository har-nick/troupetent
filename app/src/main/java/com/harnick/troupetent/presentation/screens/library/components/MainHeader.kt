package com.harnick.troupetent.presentation.screens.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QueueMusic
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harnick.troupetent.common.util.BandcampConstants

@Composable
private fun MainHeaderProfilePicture(profilePictureId: Long, modifier: Modifier) {
	AsyncImage(
		"${BandcampConstants.ART_URL}/${profilePictureId}_3.jpg", "User profile picture", modifier.then(
			Modifier
				.fillMaxHeight()
				.clip(CircleShape)
		)
	)
}

@Composable
private fun MainHeaderIconButton(
	icon: ImageVector, descriptor: String
) {
	IconButton(onClick = { /*TODO*/ }) {
		Icon(
			icon, descriptor, Modifier.size(28.dp)
		)
	}
}

@Composable
fun MainHeader(
	profilePictureId: Long?,
	greeting: String?,
	username: String?
) {
	Row(
		Modifier
			.clickable(enabled = false) {}
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
			Box(
				Modifier
					.aspectRatio(1f)
					.fillMaxHeight()
			) {
				profilePictureId?.also {
					MainHeaderProfilePicture(
						profilePictureId, Modifier.align(Alignment.Center)
					)
				} ?: CircularProgressIndicator(Modifier.align(Alignment.Center))
			}
			
			Spacer(Modifier.width(15.dp))
			
			if (!username.isNullOrEmpty() && !greeting.isNullOrEmpty()) {
				HeaderGreeting(greeting, username)
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