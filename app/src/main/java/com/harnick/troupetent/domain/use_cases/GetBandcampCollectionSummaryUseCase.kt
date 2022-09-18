package com.harnick.troupetent.domain.use_cases

import android.content.Context
import com.harnick.troupetent.common.util.Resource
import com.harnick.troupetent.data.model.BandcampExceptions.SyncRequiredException
import com.harnick.troupetent.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.data.remote.bandcamp.dto.ErrorResponseEntity
import com.harnick.troupetent.data.remote.bandcamp.dto.collection_summary.CollectionSummaryResponseEntity
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionSummary
import com.harnick.troupetent.domain.model.bandcamp.toBandcampCollectionSummary
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GetBandcampCollectionSummaryUseCase @Inject constructor(
	private val api: BandcampApi,
	@ApplicationContext private val appContext: Context
) {
	operator fun invoke() = flow {
		lateinit var hoistedResponseBody: String
		
		try {
			emit(Resource.Fetching())
			
			val response = api.fetchCollectionSummary()
			
			hoistedResponseBody = response.bodyAsText()
			
			val serializedSummary: CollectionSummaryResponseEntity =
				Json.decodeFromString(hoistedResponseBody)
			
			emit(Resource.Success(serializedSummary.collectionSummaryEntity.toBandcampCollectionSummary()))
		} catch (e: SerializationException) {
			val serializedError: ErrorResponseEntity =
				Json.decodeFromString(hoistedResponseBody)
			
			emit(Resource.Error(serializedError.errorMessage))
		} catch (e: Exception) {
			emit(Resource.Loading())
			val cachedSummaryUri = java.io.File(appContext.cacheDir, "summary.json")
			
			if (!cachedSummaryUri.isFile) {
				throw SyncRequiredException
			}
			
			val serializedSummaryCache: BandcampCollectionSummary =
				Json.decodeFromString(cachedSummaryUri.readText())
			
			emit(Resource.Success(serializedSummaryCache))
		} catch (e: SyncRequiredException) {
			emit(Resource.Error("No cached files available"))
		}
	}
}