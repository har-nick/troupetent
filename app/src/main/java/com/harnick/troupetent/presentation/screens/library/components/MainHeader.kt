package com.harnick.troupetent.presentation.screens.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QueueMusic
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harnick.troupetent.common.util.BandcampConstants
import com.harnick.troupetent.presentation.screens.components.CustomIconButton

@Composable
fun MainHeader(
	profilePictureId: Long?,
	username: String?
) {
	val profilePic =
	Column(
		Modifier
			.clickable(enabled = false) {}                                                                // Swallow touch events to prevent missclicks
			.shadow(
				10.dp,
				MaterialTheme.shapes.large
			)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colorScheme.primaryContainer)
			.fillMaxWidth()
	) {
		Row(
			Modifier
				.padding(
					10.dp,
					WindowInsets.systemBars
						.asPaddingValues()
						.calculateTopPadding()
						.plus(10.dp),
					10.dp,
					10.dp
				)
				.fillMaxWidth(),
			Arrangement.SpaceBetween,
			Alignment.CenterVertically
		) {
			Row {
				Box(
					Modifier
						.size(50.dp)
						.clip(CircleShape),
				) {
					if (profilePictureId == null) {
						CircularProgressIndicator(
							Modifier.align(Alignment.Center),
							MaterialTheme.colorScheme.onPrimaryContainer
						)
					} else {
						AsyncImage(
							"${BandcampConstants.ART_URL}/${profilePictureId}_3.jpg",
							"User profile picture",
							Modifier.fillMaxSize()
						)
					}
				}

				Spacer(Modifier.width(15.dp))

				if (!username.isNullOrEmpty()) {
					HeaderGreeting(username)
				}
			}

			Row(
				Modifier.padding(10.dp, 0.dp),
				Arrangement.SpaceAround,
				Alignment.CenterVertically
			) {
				CustomIconButton(
					Icons.Rounded.QueueMusic,
					"Open Music Queue",
					Modifier.size(30.dp)
				) {

				}

				Spacer(Modifier.width(30.dp))

				CustomIconButton(
					Icons.Rounded.Settings,
					"Open Settings",
					Modifier.size(30.dp)
				) {

				}
			}
		}
	}
}