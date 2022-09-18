package com.harnick.troupetent.presentation.screens.player.components

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay

@Composable
fun MarqueeText(
	text: String,
	textWeight: FontWeight,
	textStyle: TextStyle
) {
	val scrollState = rememberScrollState()
	val shouldAnimate by remember {
		mutableStateOf(true)
	}
	LaunchedEffect(shouldAnimate) {
		scrollState.animateScrollTo(
			scrollState.maxValue + 50,
			animationSpec = tween(
				(text.length * 80),
				1000,
				easing = CubicBezierEasing(0f, 0f, 0f, 0f)
			)
		)
		delay(1000)
		scrollState.animateScrollTo(0)
	}
	
	Text(
		text,
		Modifier
			.fillMaxWidth()
			.horizontalScroll(scrollState, false),
		MaterialTheme.colorScheme.onSurface,
		fontWeight = textWeight,
		maxLines = 1,
		style = textStyle
	)
}