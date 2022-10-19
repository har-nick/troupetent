package com.harnick.troupetent.domain.use_cases

import android.content.Context
import com.harnick.troupetent.common.util.Resource
import com.harnick.troupetent.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.domain.model.BandcampExceptions.SyncRequiredException
import com.harnick.troupetent.domain.model.bandcamp.BandcampUserData
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject

class GetBandcampUserDataUseCase @Inject constructor(
	private val api: BandcampApi,
	@ApplicationContext private val appContext: Context
) {
	operator fun invoke(
		username: String
	) = flow {
		lateinit var hoistedUserDataUri: File
		
		try {
			emit(Resource.Loading())
			
			val userDataUri = File(appContext.dataDir, "user-data.json")
			hoistedUserDataUri = userDataUri
			
			if (!userDataUri.isFile) {
				throw SyncRequiredException
			}
			
			val serializedData: BandcampUserData = Json.decodeFromString(userDataUri.readText())
			
			emit(Resource.Success(serializedData))
		} catch (e: SyncRequiredException) {
			emit(Resource.Fetching())
			
			val rawUserPage = api.fetchUserPage(username).bodyAsText()
			val userImageId = rawUserPage
				.substringAfter("popupImage\" href=\"https://f4.bcbits.com/img/")
				.substringBefore("_20").toLong()
			
			hoistedUserDataUri.writeText(
				Json.encodeToString(
					BandcampUserData(
						username,
						userImageId
					)
				)
			)
			
			val serializedData = BandcampUserData(
				username,
				userImageId
			)
			
			emit(Resource.Success(serializedData))
		}
	}
}