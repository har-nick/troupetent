package com.harnick.troupetent.presentation.screens.login

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
import com.harnick.troupetent.common.util.findActivity
import com.harnick.troupetent.presentation.screens.NavGraphs
import com.harnick.troupetent.presentation.screens.destinations.LibraryScreenDestination
import com.harnick.troupetent.presentation.screens.login.components.BandcampWebview
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
	val intent = activity?.intent
	val loginUrl = intent?.data ?: "https://bandcamp.com/login"
	
	LaunchedEffect(true) {
		loginViewModel.uiEvent.collect { event ->
			if (event is LoginEvent.TokenSaved) {
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
		) { BandcampWebview(loginUrl.toString(), loginViewModel) }
	}
}