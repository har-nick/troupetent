package com.harnick.troupetent.domain.use_cases

import com.harnick.troupetent.common.util.Resource
import com.harnick.troupetent.domain.repository.EncRepo
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class GetBandcampLoginTokenUseCase @Inject constructor(
	private val encRepo: EncRepo
) {
	operator fun invoke(path: File) = flow<Resource<String>> {
		emit(Resource.Loading())

		try {
			encRepo.decryptData(path)
		} catch (e: Exception) {
			emit(Resource.Error("Could not decrypt token: ${e.message}"))
		}
	}
}