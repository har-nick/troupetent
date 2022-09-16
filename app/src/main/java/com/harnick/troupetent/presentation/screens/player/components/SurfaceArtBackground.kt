package com.harnick.troupetent.presentation.screens.player.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.request.ImageRequest
import com.harnick.troupetent.common.util.BandcampConstants
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionItem

@Composable
fun SurfaceArtBackround(
	bandcampCollectionItem: BandcampCollectionItem
) {
	val loader = ImageLoader(LocalContext.current)
	val req = ImageRequest.Builder(LocalContext.current)
		.data("${BandcampConstants.ART_URL}/a${bandcampCollectionItem.itemArtId}_4.jpg")
		.build()
}