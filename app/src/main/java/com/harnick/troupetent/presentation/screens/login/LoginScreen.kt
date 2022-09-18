package com.harnick.troupetent.presentation.screens.login

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.harnick.troupetent.presentation.screens.destinations.LibraryScreenDestination
import com.harnick.troupetent.presentation.screens.login.components.BandcampWebview
import com.harnick.troupetent.presentation.screens.login.components.OnboardingButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(true)
@Destination
@Composable
fun LoginScreen(
	navigator: DestinationsNavigator,
	loginViewModel: LoginViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	val loginState = loginViewModel.state
	
	LaunchedEffect(true) {
		loginViewModel.uiEvent.collect { event ->
			when (event) {
				LoginEvent.OpenDefaultLinks -> {
					val openLinkIntent = Intent(Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS).apply {
						this.data = Uri.fromParts("package", "com.harnick.troupetent", null)
						this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
					}
					
					context.startActivity(openLinkIntent)
				}
				
				LoginEvent.OpenWebView -> {}
				
				is LoginEvent.TokenFound -> {}
				
				is LoginEvent.TokenSaved -> {
					navigator.navigate(LibraryScreenDestination)
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
				BandcampWebview()
			}
			
			Text(
				"Before logging in, make sure Troupetent can open verification links.",
				Modifier.padding(15.dp, 0.dp),
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.bodyLarge
			)
			
			Spacer(Modifier.height(15.dp))
			
			OnboardingButton(
				{ loginViewModel.onEvent(LoginEvent.OpenDefaultLinks) },
				"Open Supported Links",
				!loginState.canOpenLinks
			)
			
			Spacer(Modifier.height(10.dp))
			
			OnboardingButton(
				onClick = {
					loginViewModel.onEvent(LoginEvent.OpenWebView)
				},
				text = "Login to Bandcamp",
				loginState.canOpenLinks
			)
		}
	}
}