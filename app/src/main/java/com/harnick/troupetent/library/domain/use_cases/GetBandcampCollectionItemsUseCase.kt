package com.harnick.troupetent.library.domain.use_cases

import com.harnick.troupetent.core.api.bandcamp.data.BandcampApi
import com.harnick.troupetent.core.encryption.domain.repository.EncryptionRepo
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import com.harnick.troupetent.core.util.Resource
import com.harnick.troupetent.library.domain.bandcamp.BandcampCollectionSummary
import com.harnick.troupetent.library.domain.bandcamp.toBandcampLibraryData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBandcampCollectionItemsUseCase @Inject constructor(
	private val bandcampApi: BandcampApi,
	private val encryptionRepo: EncryptionRepo,
	private val userDataRepo: UserDataRepo
) {
	operator fun invoke(collectionSummary: BandcampCollectionSummary) = flow {
		try {
			emit(Resource.Fetching("Downloading library data..."))
			
			val encryptedToken = userDataRepo.loadUserData().first().userToken
			
			val token: String = if (encryptedToken != null) {
				encryptionRepo.decryptData(encryptedToken.first, encryptedToken.second)
			} else throw Exception("Missing user token.")
			
			val itemsResponseEntity = bandcampApi.fetchCollectionItems(
				collectionSummary.fanId,
				collectionSummary.itemLookupList.size.toLong(),
				token
			)
			
			val libraryData = itemsResponseEntity.toBandcampLibraryData()
			
			emit(Resource.Success(libraryData))
		} catch (e: Exception) {
			emit(Resource.Error("${e.message}"))
		}
	}
}