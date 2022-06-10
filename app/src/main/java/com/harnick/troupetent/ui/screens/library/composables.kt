package com.harnick.troupetent.ui.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harnick.troupetent.ui.widgets.albumCard

@Composable
fun libraryScreen(navController: NavController) {
  Surface(
	color = Color.Magenta,
	modifier = Modifier.fillMaxWidth()
  ) {
	Column(
	  modifier = Modifier
		.fillMaxWidth()
		.padding(
		  horizontal = 10.dp
		)
	) {
	  LazyVerticalGrid(
		columns = GridCells.Fixed(3),
		horizontalArrangement = Arrangement.spacedBy(10.dp),
		verticalArrangement = Arrangement.spacedBy(10.dp),
		modifier = Modifier
		  .background(color = Color.Black)
		  .statusBarsPadding()
	  ) {
		items(40) {
		  albumCard("TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE TITLE ", "ARTIST ARTIST ARTIST ARTIST ARTIST ARTIST ARTIST ")
		}
	  }
	}

  }
}