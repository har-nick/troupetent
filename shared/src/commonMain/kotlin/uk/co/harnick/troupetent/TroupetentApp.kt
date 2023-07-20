package uk.co.harnick.troupetent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.koinInject
import uk.co.harnick.troupetent.core.account.domain.repository.model.LocalAccount
import uk.co.harnick.troupetent.core.account.presentation.AccountViewModel
import uk.co.harnick.troupetent.core.ui.theme.presentation.TroupetentTheme
import uk.co.harnick.troupetent.features.library.presentation.LibraryScreen
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingScreen

@Composable
fun TroupetentApp() {
    val accountVM = koinInject<AccountViewModel>()
    val currentAccount by accountVM.currentAccount.collectAsState()

    val initialScreen: Screen? = when (currentAccount) {
        null -> OnboardingScreen
        is LocalAccount -> LibraryScreen
        else -> null
    }

    TroupetentTheme {
        initialScreen?.let { Navigator(screen = initialScreen) }
    }
}
