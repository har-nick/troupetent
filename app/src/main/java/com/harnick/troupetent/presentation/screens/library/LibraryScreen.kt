package com.harnick.troupetent.presentation.screens.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.harnick.troupetent.presentation.screens.destinations.PlayerScreenDestination
import com.harnick.troupetent.presentation.screens.library.LibraryEvent.NavigateToPlayer
import com.harnick.troupetent.presentation.screens.library.components.LibraryFlowRow
import com.harnick.troupetent.presentation.screens.library.components.MainHeader
import com.harnick.troupetent.presentation.screens.library.components.StatusMessage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun LibraryScreen(
	navigator: DestinationsNavigator,
	libraryViewModel: LibraryViewModel = hiltViewModel()
) {
	val libraryState = libraryViewModel.state
	val context = LocalContext.current
	
	LaunchedEffect(true) {
		libraryViewModel.uiEvent.collect { event ->
			when (event) {
				is NavigateToPlayer -> {
					navigator.navigate(
						PlayerScreenDestination(
							event.JSONEncodedItem,
							event.JSONEncodedTrackList
						)
					)
				}
				else -> {}
			}
		}
	}
	
	Scaffold(
		topBar = {
			MainHeader(
				libraryState.userProfilePictureId,
				libraryState.username
			)
		},
		snackbarHost = { SnackbarHost(libraryState.snackbarState) }
	) { headSizeAsPadding ->
		
		LazyColumn(
			Modifier
				.padding(
					0.dp,
					headSizeAsPadding.calculateTopPadding(),
					0.dp,
					0.dp
				)
				.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Top
		) {
			libraryState.statusMessage?.also {
				item {
					StatusMessage(libraryState.statusMessage)
				}
			}
			
			libraryState.errorMessage?.also {
				item {
					StatusMessage(libraryState.errorMessage)
				}
			}
			
			libraryState.bandcampLibraryData?.also {
				item {
					LibraryFlowRow(libraryState.bandcampLibraryData.bandcampCollectionItemList)
				}
			}
		}
	}
}