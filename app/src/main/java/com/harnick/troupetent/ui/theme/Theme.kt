package com.harnick.troupetent.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
  primary = Purple80,
  secondary = PurpleGrey80,
  tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
  primary = Purple40,
  secondary = PurpleGrey40,
  tertiary = Pink40

  /* Other default colors to override
  background = Color(0xFFFFFBFE),
  surface = Color(0xFFFFFBFE),
  onPrimary = Color.White,
  onSecondary = Color.White,
  onTertiary = Color.White,
  onBackground = Color(0xFF1C1B1F),
  onSurface = Color(0xFF1C1B1F),
  */
)

@Composable
fun TroupetentTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val systemUiController = rememberSystemUiController()
  val colorScheme = if (darkTheme) {
	DarkColorScheme
  } else {
	LightColorScheme
  }

  val view = LocalView.current

  if (!view.isInEditMode) {
	SideEffect {
	  (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
	  ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme

	  systemUiController.setSystemBarsColor(
		color = Color.Transparent,
		darkIcons = !darkTheme
	  )
	}
  }

  MaterialTheme(
	colorScheme = colorScheme,
	typography = Typography,
	content = content
  )
}