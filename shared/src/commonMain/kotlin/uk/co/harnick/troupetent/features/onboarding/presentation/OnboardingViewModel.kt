package uk.co.harnick.troupetent.features.onboarding.presentation

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uk.co.harnick.troupetent.core.account.domain.repository.AccountRepo
import uk.co.harnick.troupetent.core.di.IODispatcher
import uk.co.harnick.troupetent.core.ui.StatefulViewModel
import uk.co.harnick.troupetent.core.util.ResponseState
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingEvent.NavigateToLibrary
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingEvent.OpenWebView
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingEvent.ShowTokenField
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingEvent.TokenSubmitted

class OnboardingViewModel(
    private val accountRepo: AccountRepo,
    private val ioDispatcher: IODispatcher,
) : StatefulViewModel<OnboardingState, OnboardingEvent>(OnboardingState()) {
    override fun onEvent(event: OnboardingEvent) {
        when (event) {
            NavigateToLibrary -> sendEvent(event)
//            OpenWebView ->
            ShowTokenField -> mutableState.value = state.value.copy(tokenFieldIsVisible = true)
            is TokenSubmitted -> getAccount(event.token)
        }
    }

    private fun getAccount(token: String) = accountRepo.saveAccount(token)
        .flowOn(ioDispatcher)
        .onEach { response -> mutableState.value = state.value.copy(accountState = response) }
        .catch { throwable ->
            mutableState.value = state.value.copy(
                accountState = ResponseState.Error(throwable.message)
            )
        }
        .launchIn(vmScope)
}
