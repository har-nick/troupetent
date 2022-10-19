package com.harnick.troupetent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import com.harnick.troupetent.domain.model.AppSettings
import com.harnick.troupetent.domain.repository.DataStoreRepo
import com.harnick.troupetent.domain.repository.EncRepo
import com.harnick.troupetent.presentation.screens.NavGraphs
import com.harnick.troupetent.presentation.screens.destinations.LibraryScreenDestination
import com.harnick.troupetent.presentation.screens.destinations.LoginScreenDestination
import com.harnick.troupetent.presentation.ui.theme.TroupetentTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	
	@Inject
	lateinit var dataStoreRepo: DataStoreRepo
	
	@Inject
	lateinit var encRepo: EncRepo
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		WindowCompat.setDecorFitsSystemWindows(window, false)
		
		setContent {
			val appSettings = dataStoreRepo.getSettings().collectAsState(
				AppSettings()
			).value
			
			val isLoggedIn = try {
				encRepo.decryptData(
					File(applicationContext.dataDir, "bandcamp_token_enc")
				).isNotEmpty()
			} catch (e: IOException) {
				false
			}
			
			val startRoute = if (isLoggedIn) LibraryScreenDestination else LoginScreenDestination
			
			TroupetentTheme(appSettings.appTheme) {
				DestinationsNavHost(navGraph = NavGraphs.root, startRoute = startRoute)
			}
		}
	}
}