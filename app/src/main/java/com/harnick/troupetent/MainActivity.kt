package com.harnick.troupetent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import com.harnick.troupetent.domain.model.AppSettings
import com.harnick.troupetent.domain.repository.EncRepo
import com.harnick.troupetent.domain.repository.SettingsRepo
import com.harnick.troupetent.presentation.screens.NavGraphs
import com.harnick.troupetent.presentation.screens.destinations.LibraryScreenDestination
import com.harnick.troupetent.presentation.screens.destinations.OnboardingScreenDestination
import com.harnick.troupetent.presentation.ui.theme.TroupetentTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	
	@Inject
	lateinit var encRepo: EncRepo
	
	@Inject
	lateinit var settingsRepo: SettingsRepo
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		WindowCompat.setDecorFitsSystemWindows(window, false)
		
		val settingsFlow = settingsRepo.loadSettings()
		
		runBlocking {
			if (settingsFlow.first() == null) {
				launch(Dispatchers.IO) { settingsRepo.updateSettings(AppSettings()) }
			}
		}
		
		
		setContent {
			val appSettings = settingsFlow.collectAsState(AppSettings()).value
			
			val isLoggedIn = try {
				encRepo.decryptData(
					File(applicationContext.dataDir, "bandcamp_token_enc")
				).isNotEmpty()
			} catch (e: IOException) {
				false
			}
			
			val startRoute = if (isLoggedIn) LibraryScreenDestination else OnboardingScreenDestination
			
			TroupetentTheme(appSettings!!.appTheme) {
				DestinationsNavHost(navGraph = NavGraphs.root, startRoute = startRoute)
			}
		}
	}
}