package com.harnick.troupetent.presentation.screens.library.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.harnick.troupetent.common.util.BandcampConstants
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItem
import com.harnick.troupetent.presentation.screens.library.LibraryEvent
import com.harnick.troupetent.presentation.screens.library.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCard(
	cardSize: Dp,
	bandcampCollectionItem: BandcampCollectionItem,
	viewModel: LibraryViewModel = hiltViewModel()
) {
	OutlinedCard(
		onClick = {
			viewModel.onEvent(LibraryEvent.ItemSelected(bandcampCollectionItem))
		}
	) {
		Box(
			Modifier
				.clip(MaterialTheme.shapes.medium)
				.size(cardSize)
		) {
			AsyncImage(
				ImageRequest.Builder(LocalContext.current)
					.data("${BandcampConstants.ART_URL}/a${bandcampCollectionItem.itemArtId}_5.jpg")
					.crossfade(true)
					.build(),
				"Album art for ${bandcampCollectionItem.itemTitle}",
				Modifier.fillMaxSize()
			)
		}
		Column(
			Modifier
				.width(cardSize)
				.padding(5.dp)
		) {
			AlbumCardText(
				bandcampCollectionItem.itemTitle,
				FontWeight.Bold,
				MaterialTheme.typography.bodyMedium
			)
			AlbumCardText(
				bandcampCollectionItem.bandName,
				FontWeight.Normal,
				MaterialTheme.typography.bodySmall
			)
		}
	}
}