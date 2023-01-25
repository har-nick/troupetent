package com.harnick.troupetent.library.data

import com.harnick.troupetent.core.util.BandcampConstants
import com.harnick.troupetent.library.data.dto.collection_items.CollectionItemsRequestEntity
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import javax.inject.Inject

class BandcampLibraryApi @Inject constructor(
	private val client: HttpClient
) {
	suspend fun fetchCollectionSummary(token: String): HttpResponse {
		return client.get(BandcampConstants.SUMMARY_URL) {
			header("Cookie", "identity=$token")
		}
	}
	
	suspend fun fetchCollectionItems(
		fanId: Long,
		itemCount: Int,
		token: String
	): HttpResponse {
		val olderThanToken = "${System.currentTimeMillis()}::a::"
		
		return client.post(BandcampConstants.ITEMS_URL) {
			header("Cookie", "identity=$token")
			contentType(ContentType.Application.Json)
			setBody(
				CollectionItemsRequestEntity(
					fanId,
					olderThanToken,
					itemCount
				)
			)
		}
	}
	
	suspend fun fetchStreamableUrl(
		rawUrl: String,
		token: String
	): HttpResponse {
		return client.get(rawUrl) {
			header("Cookie", "identity=$token")
		}
	}
}