package com.harnick.troupetent.presentation.screens.onboarding

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.harnick.troupetent.presentation.screens.destinations.LoginScreenDestination
import com.harnick.troupetent.presentation.screens.onboarding.components.OnboardingSplash
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(true)
@Destination
@Composable
fun OnboardingScreen(
	navigator: DestinationsNavigator,
	onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
	val localContext = LocalContext.current
	
	LaunchedEffect(true) {
		onboardingViewModel.uiEvent.collect { event ->
			if (event is OnboardingEvent.OpenDefaultLinks) {
				val openLinkIntent = Intent(Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS).apply {
					this.data = Uri.fromParts("package", "com.harnick.troupetent", null)
					this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
				}
				
				localContext.startActivity(openLinkIntent)
			} else if (event is OnboardingEvent.OpenWebView) {
				navigator.navigate(LoginScreenDestination)
			}
		}
	}
	
	Surface(
		Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.surface)
	) {
		Column(
			Modifier.fillMaxSize(),
			Arrangement.Center,
			Alignment.CenterHorizontally
		) {
			OnboardingSplash(
				{ onboardingViewModel.onEvent(OnboardingEvent.OpenDefaultLinks) },
				{ onboardingViewModel.onEvent(OnboardingEvent.OpenWebView) }
			)
		}
	}
}