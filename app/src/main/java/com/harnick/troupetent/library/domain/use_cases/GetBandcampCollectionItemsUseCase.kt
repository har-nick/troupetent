package com.harnick.troupetent.library.domain.use_cases

import android.content.Context
import com.harnick.troupetent.library.data.BandcampLibraryApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetBandcampCollectionItemsUseCase @Inject constructor(
	private val bandcampLibraryApi: BandcampLibraryApi,
	@ApplicationContext private val appContext: Context
) {
//	operator fun invoke(
//		bandcampCollectionSummary: BandcampCollectionSummary
//	) = flow {
//		lateinit var hoistedSummaryUri: File
//		lateinit var hoistedDataUri: File
//		var hoistedResponseBody: String? = null
//
//		try {
//			emit(Resource.Loading())
//
//			val cachedSummaryUri = File(appContext.cacheDir, "summary.json")
//			val cachedDataUri = File(appContext.cacheDir, "items.json")
//
//			hoistedSummaryUri = cachedSummaryUri
//			hoistedDataUri = cachedDataUri
//
//			if (!cachedSummaryUri.isFile || !cachedDataUri.isFile) {
//				throw CustomExceptions.BandcampLibrarySyncRequired
//			}
//
//			val serializedSummaryCache: BandcampCollectionSummary =
//				Json.decodeFromString(cachedSummaryUri.readText())
//
//			if (serializedSummaryCache != bandcampCollectionSummary) {
//				throw CustomExceptions.BandcampLibrarySyncRequired
//			}
//
//			val serializedDataCache: BandcampLibraryData =
//				Json.decodeFromString(cachedDataUri.readText())
//
//			emit(Resource.Success(serializedDataCache))
//
//		} catch (e: CustomExceptions.BandcampLibrarySyncRequired) {
//			emit(Resource.Fetching())
//
//			val response = bandcampLibraryApi.fetchCollectionItems(
//				bandcampCollectionSummary.fanId,
//				bandcampCollectionSummary.itemLookupList.count()
//			)
//
//			if (response.status != HttpStatusCode.OK) {
//				throw Exception("HTTP Status Error - ${response.status}")
//			}
//
//			hoistedResponseBody = response.bodyAsText()
//
//			val serializedItems: BandcampCollectionItemsResponseEntity =
//				Json.decodeFromString(hoistedResponseBody)
//
//			val serializedData: BandcampLibraryData = serializedItems.toBandcampLibraryData()
//
//			hoistedSummaryUri.writeText(
//				Json.encodeToString(bandcampCollectionSummary)
//			)
//
//			hoistedDataUri.writeText(
//				Json.encodeToString(serializedData)
//			)
//
//			emit(Resource.Success(serializedItems.toBandcampLibraryData()))
//		} catch (e: SerializationException) {
//			if (hoistedResponseBody.isNullOrEmpty()) {                                                    // Ignore warning. Value is assigned in sync catch block
//				throw Exception(e)
//			}
//
//			val serializedError: ErrorResponseEntity =
//				Json.decodeFromString(hoistedResponseBody)
//
//			emit(Resource.Error(serializedError.errorMessage))
//		} catch (e: Exception) {
//			emit(Resource.Error("${e.message}"))
//		}
//	}
}