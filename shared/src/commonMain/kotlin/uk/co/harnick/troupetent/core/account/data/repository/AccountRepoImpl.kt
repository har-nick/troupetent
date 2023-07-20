package uk.co.harnick.troupetent.core.account.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlin.coroutines.CoroutineContext
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import uk.co.harnick.bandkit.data.BandcampApi
import uk.co.harnick.troupetent.LocalStorage
import uk.co.harnick.troupetent.core.account.domain.repository.AccountRepo
import uk.co.harnick.troupetent.core.account.domain.repository.model.LocalAccount
import uk.co.harnick.troupetent.core.account.domain.repository.model.toLocalAccount
import uk.co.harnick.troupetent.core.util.ResponseState

class AccountRepoImpl(private val db: LocalStorage) : AccountRepo {
    private val queries = db.accountEntityQueries

    override fun loadAllAccounts(
        localContext: CoroutineContext
    ): Flow<PersistentList<LocalAccount>> = queries.loadAllAccounts()
        .asFlow()
        .mapToList(localContext)
        .map { mutableList ->
            mutableList
                .map { account -> account.toLocalAccount() }
                .toPersistentList()
        }

    override fun saveAccount(token: String) = flow<ResponseState<Unit>> {
        emit(ResponseState.Fetching())

        val summary = BandcampApi.fetchSummary(token)
        val username = summary.collectionSummary.username

        val user = BandcampApi.fetchUser(username)

        emit(ResponseState.Saving())

        queries.addAccount(token, user.username, user.nickname, user.avatarId, user.bannerId)

        emit(ResponseState.Success())
    }
}
