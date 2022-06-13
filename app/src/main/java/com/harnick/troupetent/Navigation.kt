package com.harnick.troupetent

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harnick.troupetent.models.Screens
import com.harnick.troupetent.ui.screens.library.libraryScreen
import com.harnick.troupetent.ui.screens.settings.settingsScreen
import com.harnick.troupetent.ui.screens.webview.LoginWebView

@Composable
fun Navigation(token: Any) {
  val navController = rememberNavController()

  var startRoute: String = if (token is String) {
	Screens.LibraryScreen.route
  } else {
	Screens.LoginScreen.route
  }

  NavHost(navController = navController, startDestination = startRoute) {
	composable(route = Screens.LibraryScreen.route) {
	  libraryScreen(navController = navController)
	}
	composable(route = Screens.SettingsScreen.route) {
	  settingsScreen(navController = navController)
	}
	composable(route = Screens.LoginScreen.route) {
	  LoginWebView(navController = navController)
	}
  }
}