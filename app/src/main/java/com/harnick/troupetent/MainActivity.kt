package com.harnick.troupetent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import com.harnick.troupetent.core.app_settings.domain.model.AppSettings
import com.harnick.troupetent.core.app_settings.domain.repository.SettingsRepo
import com.harnick.troupetent.core.user_data.domain.model.BandcampUserData
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import com.harnick.troupetent.core.util.theme.TroupetentTheme
import com.harnick.troupetent.destinations.LibraryScreenDestination
import com.harnick.troupetent.destinations.OnboardingScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
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
		
		val appSettingsFlow = settingsRepo.loadSettings().filterNotNull()
		val userDataFlow = userDataRepo.loadUserData().filterNotNull()
		
		setContent {
			val appSettings = appSettingsFlow.collectAsState(AppSettings()).value
			val userData = userDataFlow.collectAsState(BandcampUserData()).value
			
			val startRoute =
				if (userData.userToken == null) OnboardingScreenDestination else LibraryScreenDestination
			
			TroupetentTheme(appSettings.appTheme) {
				DestinationsNavHost(navGraph = NavGraphs.root, startRoute = startRoute)
			}
		}
	}
}
