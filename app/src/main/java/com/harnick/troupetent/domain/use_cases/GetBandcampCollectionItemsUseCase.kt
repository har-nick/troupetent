package com.harnick.troupetent.domain.use_cases

import android.content.Context
import com.harnick.troupetent.common.util.Resource
import com.harnick.troupetent.data.model.BandcampExceptions.SyncRequiredException
import com.harnick.troupetent.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.data.remote.bandcamp.dto.ErrorResponseEntity
import com.harnick.troupetent.data.remote.bandcamp.dto.collection_items.BandcampCollectionItemsResponseEntity
import com.harnick.troupetent.domain.model.bandcamp.BandcampCollectionSummary
import com.harnick.troupetent.domain.model.bandcamp.BandcampLibraryData
import com.harnick.troupetent.domain.model.bandcamp.toBandcampLibraryData
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject

class GetBandcampCollectionItemsUseCase @Inject constructor(
	private val api: BandcampApi,
	@ApplicationContext private val appContext: Context
) {
	operator fun invoke(
		bandcampCollectionSummary: BandcampCollectionSummary
	) = flow {
		lateinit var hoistedSummaryUri: File
		lateinit var hoistedDataUri: File
		var hoistedResponseBody: String? = null

		try {
			emit(Resource.Loading())

			val cachedSummaryUri = File(appContext.cacheDir, "summary.json")
			val cachedDataUri = File(appContext.cacheDir, "items.json")

			hoistedSummaryUri = cachedSummaryUri
			hoistedDataUri = cachedDataUri

			if (!cachedSummaryUri.isFile || !cachedDataUri.isFile) {
				throw SyncRequiredException
			}

			val serializedSummaryCache: BandcampCollectionSummary =
				Json.decodeFromString(cachedSummaryUri.readText())

			if (serializedSummaryCache != bandcampCollectionSummary) {
				throw SyncRequiredException
			}

			val serializedDataCache: BandcampLibraryData =
				Json.decodeFromString(cachedDataUri.readText())

			emit(Resource.Success(serializedDataCache))

		} catch (e: SyncRequiredException) {
			emit(Resource.Fetching())

			val response = api.fetchCollectionItems(
				bandcampCollectionSummary.fanId,
				bandcampCollectionSummary.itemLookupList.count()
			)

			if (response.status != HttpStatusCode.OK) {
				throw Exception("HTTP Status Error - ${response.status}")
			}

			hoistedResponseBody = response.bodyAsText()

			val serializedItems: BandcampCollectionItemsResponseEntity =
				Json.decodeFromString(hoistedResponseBody)

			val serializedData: BandcampLibraryData = serializedItems.toBandcampLibraryData()

			hoistedSummaryUri.writeText(
				Json.encodeToString(bandcampCollectionSummary)
			)

			hoistedDataUri.writeText(
				Json.encodeToString(serializedData)
			)

			emit(Resource.Success(serializedItems.toBandcampLibraryData()))
		} catch (e: SerializationException) {
			if (hoistedResponseBody.isNullOrEmpty()) {                                                    // Ignore warning. Value is assigned in sync catch block
				throw Exception(e)
			}

			val serializedError: ErrorResponseEntity =
				Json.decodeFromString(hoistedResponseBody)

			emit(Resource.Error(serializedError.errorMessage))
		} catch (e: Exception) {
			emit(Resource.Error("${e.message}"))
		}
	}
}