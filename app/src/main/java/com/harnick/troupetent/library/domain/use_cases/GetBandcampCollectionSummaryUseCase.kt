package com.harnick.troupetent.library.domain.use_cases

import com.harnick.troupetent.core.api.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.core.encryption.domain.repository.EncryptionRepo
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import com.harnick.troupetent.core.util.Resource
import com.harnick.troupetent.library.domain.model.bandcamp.collection_summary.toBandcampCollectionSummary
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetBandcampCollectionSummaryUseCase @Inject constructor(
	private val bandcampApi: BandcampApi,
	private val encryptionRepo: EncryptionRepo,
	private val userDataRepo: UserDataRepo
) {
	operator fun invoke() = flow {
		try {
			emit(Resource.Fetching("Syncing library data..."))
			
			userDataRepo.loadEncryptedToken().onEach { encryptedToken ->
				val token: String = encryptionRepo.decryptData(encryptedToken.first, encryptedToken.second)
				
				val summaryResponse = bandcampApi.fetchCollectionSummary(token)
				val summary = summaryResponse.bandcampCollectionSummaryEntity.toBandcampCollectionSummary()
				
				emit(Resource.Success(summary))
			}.collect()
		} catch (e: Exception) {
			emit(Resource.Error("${e.message}"))
		}
	}
}