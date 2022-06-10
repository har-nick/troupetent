package com.harnick.troupetent.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun albumCard(albumTitle: String, artistName: String) {
  Card(
	shape = RectangleShape
  ) {
	Box(
	  modifier = Modifier
		.fillMaxWidth()
		.height(120.dp)
		.background(Color.Red)
	)
	Box(
	  modifier = Modifier
		.width(120.dp)
	) {
	  Text(
		text = albumTitle,
		fontWeight = FontWeight.Bold,
		fontSize = 14.sp,
		maxLines = 1,
		overflow = TextOverflow.Ellipsis,
	  )
	}
	Box(
	  modifier = Modifier
		.width(120.dp)
	) {
	  Text(
		text = artistName,
		fontSize = 12.sp,
		maxLines = 1
	  )
	}
  }
}