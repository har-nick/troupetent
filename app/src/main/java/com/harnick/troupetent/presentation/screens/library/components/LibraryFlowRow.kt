package com.harnick.troupetent.presentation.screens.library.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItem

// cardSize = ( screen width / column count) - horizontal spacing

@Composable
fun LibraryFlowRow(
	bandcampCollectionItemList: List<BandcampCollectionItem>
) {
	FlowRow(
		Modifier
			.padding(
				0.dp, 130.dp, 0.dp, 20.dp
			),
		mainAxisSpacing = 10.dp,
		crossAxisSpacing = 10.dp,
		lastLineMainAxisAlignment = FlowMainAxisAlignment.Start
	) {
		val cardSize = (LocalConfiguration.current.screenWidthDp.dp / 3) - 10.dp

		bandcampCollectionItemList.forEach { item ->
			AlbumCard(
				cardSize,
				item
			)
		}
	}
}