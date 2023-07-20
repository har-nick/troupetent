package uk.co.harnick.troupetent.core.account.presentation

import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import uk.co.harnick.troupetent.core.account.domain.repository.AccountRepo
import uk.co.harnick.troupetent.core.di.IODispatcher
import uk.co.harnick.troupetent.core.ui.StatelessViewModel

class AccountViewModel(
    private val accountRepo: AccountRepo,
    private val ioDispatcher: IODispatcher,
) : StatelessViewModel() {
    private object LoadingAccounts

    val localAccounts = accountRepo.loadAllAccounts(coroutineScope.coroutineContext)
        .flowOn(ioDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    val currentAccount = localAccounts
        .mapLatest { accounts -> accounts.firstOrNull { it.isLastLoggedIn } }
        .stateIn(
            scope = vmScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = LoadingAccounts
        )
}
