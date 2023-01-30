package com.harnick.troupetent.core.user_data.domain.use_cases

import com.harnick.troupetent.core.api.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.core.encryption.domain.repository.EncryptionRepo
import com.harnick.troupetent.core.user_data.domain.model.BandcampUserData
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import com.harnick.troupetent.core.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveBandcampUserDataUseCase @Inject constructor(
	private val bandcampApi: BandcampApi,
	private val encryptionRepo: EncryptionRepo,
	private val userDataRepo: UserDataRepo
) {
	operator fun invoke(
		token: String
	) = flow<Resource<Unit>> {
		try {
			emit(Resource.Fetching("Fetching user data..."))
			
			val collectionSummaryEntity = bandcampApi.fetchCollectionSummary(token)
			
			val username = collectionSummaryEntity.bandcampCollectionSummaryEntity.fanUsername
			
			val userPageHTML = bandcampApi.fetchUserPage(username)
			
			emit(Resource.Saving("Saving user data..."))
			
			val profilePictureId =
				if (userPageHTML.contains("fan-bio-pic")) {
					userPageHTML
						.substringAfter("<a class=\"popupImage\" href=\"https://f4.bcbits.com/img/")
						.substringBefore("_")
						.toLong()
				} else -1
			
			userDataRepo.updateUserData(
				BandcampUserData(
					profilePictureId = profilePictureId,
					username = username,
					userToken = encryptionRepo.encryptData(token)
				)
			)
			
			emit(Resource.Success())
		} catch (e: Exception) {
			emit(Resource.Error("${e.message}"))
		}
	}
}