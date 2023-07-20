package uk.co.harnick.troupetent.features.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.onEach
import org.koin.compose.koinInject
import uk.co.harnick.troupetent.features.library.presentation.LibraryScreen
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingEvent.NavigateToLibrary
import uk.co.harnick.troupetent.features.onboarding.presentation.components.LoginPrompt

object OnboardingScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val onboardingVM = koinInject<OnboardingViewModel>()
        val state by onboardingVM.state.collectAsState()
        val onEvent = onboardingVM::onEvent
        val uiEvent = onboardingVM.uiEvent

        LaunchedEffect(null) {
            uiEvent.onEach { event ->
                if (event is NavigateToLibrary) {
                    navigator.replace(LibraryScreen)
                }
            }
        }

        LoginPrompt(state.tokenFieldIsVisible, onEvent)
    }
}
