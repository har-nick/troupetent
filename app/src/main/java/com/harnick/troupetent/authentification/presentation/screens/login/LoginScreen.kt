package com.harnick.troupetent.authentification.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.harnick.troupetent.NavGraphs
import com.harnick.troupetent.authentification.presentation.screens.login.components.BandcampWebView
import com.harnick.troupetent.authentification.presentation.screens.login.components.LoginStateNotifier
import com.harnick.troupetent.core.util.findActivity
import com.harnick.troupetent.destinations.LibraryScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination
@Composable
fun LoginScreen(
	navigator: DestinationsNavigator,
	loginViewModel: LoginViewModel = hiltViewModel()
) {
	val localContext = LocalContext.current
	val loginState = loginViewModel.state
	
	val activity = localContext.findActivity()
	val verificationIntent = activity?.intent
	val startUrl = (verificationIntent?.data ?: "https://bandcamp.com/login").toString()
	
	LaunchedEffect(true) {
		loginViewModel.uiEvent.collect { event ->
			if (event is LoginEvent.UserDataSaved) {
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
		if (loginState.webViewEnabled) {
			BandcampWebView(
				startUrl,
				loginViewModel::onEvent,
				loginState.webViewVisible,
			)
		}
		
		LoginStateNotifier(
			loginState.status,
			loginState.error,
			loginViewModel::onEvent
		)
	}
}
