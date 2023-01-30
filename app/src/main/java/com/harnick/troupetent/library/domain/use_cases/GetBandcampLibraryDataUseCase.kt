package com.harnick.troupetent.library.domain.use_cases

import com.harnick.troupetent.core.api.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.core.encryption.domain.repository.EncryptionRepo
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import com.harnick.troupetent.core.util.Resource
import com.harnick.troupetent.library.domain.model.bandcamp.collection_items.toBandcampLibraryData
import com.harnick.troupetent.library.domain.model.bandcamp.collection_summary.BandcampCollectionSummary
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBandcampLibraryDataUseCase @Inject constructor(
	private val bandcampApi: BandcampApi,
	private val encryptionRepo: EncryptionRepo,
	private val userDataRepo: UserDataRepo
) {
	operator fun invoke(collectionSummary: BandcampCollectionSummary) = flow {
		try {
			emit(Resource.Fetching("Downloading library data..."))
			
			userDataRepo.loadEncryptedToken().collect { encryptedToken ->
				val token: String = encryptionRepo.decryptData(encryptedToken.first, encryptedToken.second)
				
				val itemsResponseEntity = bandcampApi.fetchCollectionItems(
					collectionSummary.fanId,
					collectionSummary.itemLookupList.size.toLong(),
					token
				)
				
				val libraryData = itemsResponseEntity.toBandcampLibraryData()
				
				emit(Resource.Success(libraryData))
			}
		} catch (e: Exception) {
			emit(Resource.Error("${e.message}"))
		}
	}
}