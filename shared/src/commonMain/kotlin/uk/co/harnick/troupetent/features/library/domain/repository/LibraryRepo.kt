package uk.co.harnick.troupetent.features.library.domain.repository

import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow
import uk.co.harnick.bandkit.domain.model.LibraryItem
import uk.co.harnick.troupetent.core.util.ResponseState

interface LibraryRepo {
    suspend fun getLibrarySummary(token: String): Flow<ResponseState<PersistentList<Long>>>

    suspend fun getLibraryItems(
        token: String,
        userId: Long,
        itemCount: Int
    ): Flow<ResponseState<PersistentList<LibraryItem>>>
}
