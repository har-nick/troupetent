package com.harnick.troupetent.presentation.screens.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomIconButton(
	icon: ImageVector,
	contentDescriptor: String,
	modifier: Modifier,
	onClick: () -> Unit
) {
	IconButton(
		onClick,
		modifier
	) {
		Icon(
			icon,
			contentDescriptor,
			Modifier.fillMaxSize()
		)
	}
}