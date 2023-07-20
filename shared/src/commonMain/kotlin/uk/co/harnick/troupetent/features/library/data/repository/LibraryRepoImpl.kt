package uk.co.harnick.troupetent.features.library.data.repository

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uk.co.harnick.bandkit.data.BandcampApi
import uk.co.harnick.bandkit.domain.model.LibraryItem
import uk.co.harnick.bandkit.domain.model.toLibraryItems
import uk.co.harnick.troupetent.core.util.ResponseState
import uk.co.harnick.troupetent.features.library.domain.repository.LibraryRepo

class LibraryRepoImpl : LibraryRepo {
    override suspend fun getLibrarySummary(
        token: String
    ): Flow<ResponseState<PersistentList<Long>>> = flow {
        emit(ResponseState.Fetching())

        val summaryDto = BandcampApi.fetchSummary(token)

        val summaryList = summaryDto.collectionSummary.trAlbumLookupList
            .map { it.value.itemId }
            .toPersistentList()

        emit(ResponseState.Success(summaryList))
    }

    override suspend fun getLibraryItems(
        token: String,
        userId: Long,
        itemCount: Int
    ): Flow<ResponseState<PersistentList<LibraryItem>>> = flow {
        emit(ResponseState.Fetching())

        val itemsDto = BandcampApi.fetchItems(token, userId, itemCount)
        val items = itemsDto.toLibraryItems().toPersistentList()

        emit(ResponseState.Success(items))
    }
}
