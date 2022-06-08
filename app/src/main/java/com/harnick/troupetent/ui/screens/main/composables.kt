package com.harnick.troupetent.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
  Column(
	verticalArrangement = Arrangement.Center,
	modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 50.dp)
  ) {
	Text(text = "Hello!")
  }
}