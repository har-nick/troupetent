package com.harnick.troupetent.library.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.harnick.troupetent.library.presentation.components.StatusMessage
import com.harnick.troupetent.library.presentation.components.header.MainHeader
import com.harnick.troupetent.library.presentation.components.library_grid.LibraryFlowRow
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
	
	LaunchedEffect(null) {
		libraryViewModel.uiEvent.collect { event ->
			when (event) {
				is LibraryEvent.NavigateToPlayer -> {
//					navigator.navigate(
//						PlayerScreenDestination(
//							event.JSONEncodedItem, event.JSONEncodedTrackList
//						)
//					)
				}
				else -> {}
			}
		}
	}
	
	Scaffold(
		topBar = {
			MainHeader(
				libraryState.userProfilePictureId, libraryState.headerGreeting, libraryState.username
			)
		},
		snackbarHost = { SnackbarHost(libraryState.snackbarState) }) { headerSizeAsPadding ->
		LazyColumn(
			Modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
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
					Spacer(
						Modifier.height(
							headerSizeAsPadding.calculateTopPadding()
						)
					)
					LibraryFlowRow(
						libraryState.bandcampLibraryData.bandcampCollectionItemList
					)
				}
			}
		}
	}
}