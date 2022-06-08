package com.harnick.troupetent

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harnick.troupetent.models.Screens
import com.harnick.troupetent.ui.screens.main.MainScreen

@Composable
fun Navigation() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
    composable(route = Screens.MainScreen.route) {
      MainScreen()
    }
  }
}