package com.harnick.troupetent.presentation.screens.library.components.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import com.harnick.troupetent.common.util.BandcampConstants

@Composable
fun MainHeaderProfilePicture(profilePictureId: Long) {
	Box(
		Modifier
			.clip(CircleShape)
			.aspectRatio(1f)
			.fillMaxHeight()
	) {
		AsyncImage(
			"${BandcampConstants.ART_URL}/${profilePictureId}_3.jpg",
			"User profile picture",
			Modifier
				.fillMaxSize()
		)
	}
}