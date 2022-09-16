package com.harnick.troupetent.presentation.screens.player.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.harnick.troupetent.common.util.BandcampConstants
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItem

@Composable
fun AlbumArt(
	bandcampCollectionItem: BandcampCollectionItem
) {
	AsyncImage(
		ImageRequest.Builder(LocalContext.current)
			.data("${BandcampConstants.ART_URL}/a${bandcampCollectionItem.itemArtId}_5.jpg")
			.build(),
		"Album art for ${bandcampCollectionItem.itemTitle}",
		Modifier.fillMaxSize()
	)
}