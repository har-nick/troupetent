package com.harnick.troupetent.presentation.screens.login

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.harnick.troupetent.common.util.findActivity
import com.harnick.troupetent.presentation.screens.NavGraphs
import com.harnick.troupetent.presentation.screens.destinations.LibraryScreenDestination
import com.harnick.troupetent.presentation.screens.login.components.BandcampWebview
import com.harnick.troupetent.presentation.screens.login.components.OnboardingSplash
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@RootNavGraph(true)
@Destination
@Composable
fun LoginScreen(
	navigator: DestinationsNavigator,
	loginViewModel: LoginViewModel = hiltViewModel()
) {
	val localContext = LocalContext.current
	val loginState = loginViewModel.state
	
	val activity = localContext.findActivity()
	val intent = activity?.intent
	val loginUrl = intent?.data ?: "https://bandcamp.com/login"
	
	LaunchedEffect(true) {
		loginViewModel.uiEvent.collect { event ->
			if (event is LoginEvent.OpenDefaultLinks) {
				val openLinkIntent = Intent(Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS).apply {
					this.data = Uri.fromParts("package", "com.harnick.troupetent", null)
					this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
				}
				
				localContext.startActivity(openLinkIntent)
			} else if (event is LoginEvent.TokenSaved) {
				navigator.navigate(LibraryScreenDestination) {
					popUpTo(NavGraphs.root) { inclusive = true }
				}
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
			if (loginState.webViewEnabled) {
				BandcampWebview(loginUrl.toString())
			} else {
				OnboardingSplash(
					{ loginViewModel.onEvent(LoginEvent.OpenDefaultLinks) },
					{ loginViewModel.onEvent(LoginEvent.OpenWebView) }
				)
			}
		}
	}
}