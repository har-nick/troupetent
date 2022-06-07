package com.harnick.troupetent.models

sealed class Screens(val route: String) {
  object MainScreen : Screens("main_screen")
  object LibraryScreen : Screens("library_screen")
  object LoginScreen : Screens("login_screen")
  object PlayerScreen : Screens("player_screen")
  object SettingsScreen : Screens("settings_screen")
}
