package com.harnick.troupetent

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harnick.troupetent.models.Screens
import com.harnick.troupetent.ui.screens.library.libraryScreen
import com.harnick.troupetent.ui.screens.settings.settingsScreen

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = Screens.LibraryScreen.route) {
	composable(route = Screens.LibraryScreen.route) {
	  libraryScreen(navController = navController)
	}
	composable(route = Screens.SettingsScreen.route) {
	  settingsScreen(navController = navController)
	}
  }
}