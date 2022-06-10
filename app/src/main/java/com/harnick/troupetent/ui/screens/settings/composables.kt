package com.harnick.troupetent.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun settingsScreen(navController: NavController) {
  Column(
	verticalArrangement = Arrangement.Center,
	modifier = Modifier
	  .fillMaxWidth()
	  .padding(horizontal = 50.dp)
  ) {
	Text(text = "Settings Screen")
  }
}