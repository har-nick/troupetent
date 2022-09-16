package com.harnick.troupetent.domain.use_cases

import com.harnick.troupetent.common.util.Resource
import com.harnick.troupetent.domain.repository.EncRepo
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class SaveBandcampLoginTokenUseCase @Inject constructor(
	private val encRepo: EncRepo
) {
	operator fun invoke(path: File, token: String) = flow<Resource<Unit>> {
		emit(Resource.Saving())

		try {
			encRepo.encryptData(
				path,
				token
			)

			emit(Resource.Success())
		} catch (e: Exception) {
			emit(Resource.Error("Could not save token: ${e.message}"))
		}
	}
}