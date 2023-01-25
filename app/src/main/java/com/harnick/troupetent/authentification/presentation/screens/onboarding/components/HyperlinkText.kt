package com.harnick.troupetent.authentification.presentation.screens.onboarding.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

@Composable
fun HyperlinkText(
	modifier: Modifier = Modifier,
	textContent: String,
	textColour: Color = MaterialTheme.colorScheme.primary,
	fontSize: TextUnit = TextUnit.Unspecified,
	fontWeight: FontWeight = FontWeight.Medium,
	underline: Boolean = true,
	linkUrl: String
) {
	val clickableTextContent = buildAnnotatedString {
		append(textContent)
		addStyle(
			SpanStyle(
				textColour,
				fontSize,
				fontWeight,
				textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
			),
			0,
			textContent.length
		)
	}
	val uriHandler = LocalUriHandler.current
	ClickableText(clickableTextContent, modifier, onClick = { uriHandler.openUri(linkUrl) })
}