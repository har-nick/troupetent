package com.harnick.troupetent.library.domain.use_cases

import android.content.Context
import com.harnick.troupetent.library.data.BandcampLibraryApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetBandcampCollectionSummaryUseCase @Inject constructor(
	@ApplicationContext private val appContext: Context,
	private val bandcampLibraryApi: BandcampLibraryApi
) {
//	operator fun invoke() = flow {
//		lateinit var hoistedResponseBody: String
//
//		try {
//			emit(Resource.Fetching())
//
//			val response = bandcampLibraryApi.fetchCollectionSummary()
//
//			hoistedResponseBody = response.bodyAsText()
//
//			val serializedSummary: CollectionSummaryResponseEntity =
//				Json.decodeFromString(hoistedResponseBody)
//
//			emit(Resource.Success(serializedSummary.collectionSummaryEntity.toBandcampCollectionSummary()))
//		} catch (e: SerializationException) {
//			val serializedError: ErrorResponseEntity =
//				Json.decodeFromString(hoistedResponseBody)
//
//			emit(Resource.Error(serializedError.errorMessage))
//		} catch (e: Exception) {
//			emit(Resource.Loading())
//			val cachedSummaryUri = java.io.File(appContext.cacheDir, "summary.json")
//
//			if (!cachedSummaryUri.isFile) {
//				throw CustomExceptions.BandcampLibrarySyncRequired
//			}
//
//			val serializedSummaryCache: BandcampCollectionSummary =
//				Json.decodeFromString(cachedSummaryUri.readText())
//
//			emit(Resource.Success(serializedSummaryCache))
//		} catch (e: Exception) {
//			emit(Resource.Error("No cached files available"))
//		}
//	}
}