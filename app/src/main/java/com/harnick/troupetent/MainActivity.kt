package com.harnick.troupetent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.harnick.troupetent.core.app_settings.domain.model.AppSettings
import com.harnick.troupetent.core.app_settings.domain.repository.SettingsRepo
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import com.harnick.troupetent.core.util.theme.TroupetentTheme
import com.harnick.troupetent.destinations.LibraryScreenDestination
import com.harnick.troupetent.destinations.OnboardingScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Check for when Room team fix their null on init bug.

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	@Inject
	lateinit var settingsRepo: SettingsRepo
	
	@Inject
	lateinit var userDataRepo: UserDataRepo
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window, false)
		
		var isSplashScreenVisible by mutableStateOf(true)
		
		installSplashScreen().setKeepOnScreenCondition { isSplashScreenVisible }
		
		setContent {
			val appSettings: AppSettings by settingsRepo.loadSettings().collectAsState(AppSettings())
			val isLoggedIn: Boolean? by userDataRepo.getLoginStatus().collectAsState(null)
			
			val startRoute = when (isLoggedIn) {
				null -> null
				false -> OnboardingScreenDestination
				true -> LibraryScreenDestination
			}
			
			if (startRoute != null) {
				isSplashScreenVisible = false
				
				TroupetentTheme(appSettings.appTheme) {
					DestinationsNavHost(NavGraphs.root, startRoute = startRoute)
				}
			}
		}
	}
}
