package com.harnick.troupetent.ui.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harnick.troupetent.ui.widgets.albumCard
import com.harnick.troupetent.util.CryptoUtils

@Composable
fun libraryScreen(navController: NavController) {
  val token: String = CryptoUtils.decryptFile(LocalContext.current, "token")

  println(token)

  LazyVerticalGrid(
	contentPadding = WindowInsets.systemBars.only(WindowInsetsSides.Vertical).add(
	  WindowInsets(top = 16.dp, right = 8.dp, bottom = 16.dp, left = 8.dp)
	).asPaddingValues(),
	columns = GridCells.Fixed(3),
	horizontalArrangement = Arrangement.spacedBy(10.dp),
	verticalArrangement = Arrangement.spacedBy(10.dp),
	modifier = Modifier
	  .background(color = Color.Black)
  ) {
	items(40) {
	  albumCard(
		"TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE ",
		"ARTIST ARTIST ARTIST ARTIST ARTIST ARTIST ARTIST "
	  )
	}
  }
}