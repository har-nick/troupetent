package com.harnick.troupetent.presentation.screens.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.harnick.troupetent.presentation.screens.player.components.AlbumArt
import com.harnick.troupetent.presentation.screens.player.components.MarqueeText
import com.harnick.troupetent.presentation.screens.player.components.PlayerBar
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
	navArgsDelegate = PlayerNavArgs::class
)
@Composable
fun PlayerScreen(
	playerViewModel: PlayerViewModel = hiltViewModel()
) {
	val playerState = playerViewModel.state
	val context = LocalContext.current

	LaunchedEffect(true) {
		playerViewModel.uiEvent.collect { event ->
			when (event) {
				else -> {}
			}
		}
	}

	Surface(
		Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.surface)
	) {
		Column(
			Modifier
				.fillMaxSize()
				.padding(40.dp, 0.dp)
		) {
			Column(
				Modifier.weight(2.5f),
				Arrangement.Bottom,
				Alignment.CenterHorizontally
			) {
				Box(
					Modifier
						.aspectRatio(1f)
						.fillMaxWidth()
				) {
					AlbumArt(playerState.bandcampCollectionItem)
				}
				Spacer(Modifier.height(10.dp))
				MarqueeText(
					playerState.currentBandcampCollectionItemTrack.title,
					FontWeight.Bold,
					MaterialTheme.typography.headlineMedium
				)
				Spacer(Modifier.height(2.dp))
				MarqueeText(
					playerState.currentBandcampCollectionItemTrack.artist,
					FontWeight.SemiBold,
					MaterialTheme.typography.titleLarge
				)
			}
			Column(
				Modifier.weight(1f),
				Arrangement.Top,
				Alignment.CenterHorizontally
			) {
				Spacer(Modifier.height(20.dp))
				Box(
					Modifier
						.fillMaxWidth()
						.height(2.dp)
						.background(Color.Gray)
				)
				Spacer(Modifier.height(20.dp))
				PlayerBar(playerState.buffering)
			}
		}
	}
}