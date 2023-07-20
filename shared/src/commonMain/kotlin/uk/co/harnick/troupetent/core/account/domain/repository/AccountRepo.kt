package uk.co.harnick.troupetent.core.account.domain.repository

import kotlin.coroutines.CoroutineContext
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow
import uk.co.harnick.troupetent.core.account.domain.repository.model.LocalAccount
import uk.co.harnick.troupetent.core.util.ResponseState

interface AccountRepo {
    fun loadAllAccounts(
        localContext: CoroutineContext
    ): Flow<PersistentList<LocalAccount>>

    fun saveAccount(token: String): Flow<ResponseState<Unit>>
}
