package com.harnick.troupetent.core.api.bandcamp.data

import com.harnick.troupetent.core.api.bandcamp.data.dto.ErrorResponseEntity
import com.harnick.troupetent.core.util.BandcampConstants
import com.harnick.troupetent.library.data.dto.collection_summary.CollectionSummaryResponseEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.SerializationException
import javax.inject.Inject

class BandcampApi @Inject constructor(
	private val client: HttpClient
) {
	suspend fun fetchCollectionSummary(token: String): CollectionSummaryResponseEntity {
		var hoistedResponse: HttpResponse? = null
		
		try {
			val collectionEndpointResponse = client.get(BandcampConstants.SUMMARY_URL) {
				header("Cookie", "identity=$token")
			}
			
			hoistedResponse = collectionEndpointResponse
			
			if (collectionEndpointResponse.status != HttpStatusCode.OK) {
				throw Exception(collectionEndpointResponse.status.description)
			}
			
			return collectionEndpointResponse.body()
		} catch (e: SerializationException) {
			var errorResponseEntity: ErrorResponseEntity? = null
			
			if (hoistedResponse != null) {
				errorResponseEntity = hoistedResponse.body()
			}
			
			throw Exception("${errorResponseEntity?.errorMessage}")
		} catch (e: SerializationException) {
			throw SerializationException("Failed to serialize Collection Summary")
		}
	}
	
	suspend fun fetchUserPage(username: String): String {
		val userPageEndpointResponse = client.get("${BandcampConstants.BASE_URL}/$username")
		
		if (userPageEndpointResponse.status != HttpStatusCode.OK) {
			throw Exception(userPageEndpointResponse.status.description)
		}
		
		val userPage = userPageEndpointResponse.bodyAsText()
		
		if (userPage.contains("Sorry, that something isn’t here.")) {
			throw ClientRequestException(
				userPageEndpointResponse,
				"Troupetent couldn't find your fan page."
			)
		}
		
		return userPage
	}
}