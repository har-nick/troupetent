package com.harnick.troupetent.library.domain.use_cases

import com.harnick.troupetent.core.api.bandcamp.data.BandcampApi
import com.harnick.troupetent.core.encryption.domain.repository.EncryptionRepo
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import com.harnick.troupetent.core.util.Resource
import com.harnick.troupetent.library.domain.bandcamp.toBandcampCollectionSummary
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBandcampCollectionSummaryUseCase @Inject constructor(
	private val bandcampApi: BandcampApi,
	private val encryptionRepo: EncryptionRepo,
	private val userDataRepo: UserDataRepo
) {
	operator fun invoke() = flow {
		try {
			emit(Resource.Fetching("Syncing library data..."))
			
			val encryptedToken = userDataRepo.getUserToken()
			
			val token: String = if (encryptedToken != null) {
				encryptionRepo.decryptData(encryptedToken.first, encryptedToken.second)
			} else throw Exception("Missing user token.")
			
			val summaryResponseEntity = bandcampApi.fetchCollectionSummary(token)
			val summary =
				summaryResponseEntity.bandcampCollectionSummaryEntity.toBandcampCollectionSummary()
			
			emit(Resource.Success(summary))
		} catch (e: Exception) {
			emit(Resource.Error("${e.message}"))
		}
	}
}