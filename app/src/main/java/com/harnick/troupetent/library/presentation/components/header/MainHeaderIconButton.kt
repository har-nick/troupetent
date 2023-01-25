package com.harnick.troupetent.library.presentation.components.header

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MainHeaderIconButton(
	icon: ImageVector, descriptor: String
) {
	IconButton(onClick = { /*TODO*/ }) {
		Icon(
			icon, descriptor, Modifier.size(28.dp)
		)
	}
}