package com.harnick.troupetent.data.remote.bandcamp

import android.content.Context
import com.harnick.troupetent.common.util.BandcampConstants
import com.harnick.troupetent.data.remote.bandcamp.dto.library_responses.collection_items.CollectionItemsRequestEntity
import com.harnick.troupetent.domain.repository.EncRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.io.File
import javax.inject.Inject

class BandcampApi @Inject constructor(
	@ApplicationContext private val appContext: Context,
	private val client: HttpClient,
	encRepo: EncRepo
) {
	private val token = encRepo.decryptData(
		File(appContext.dataDir, "bandcamp_token_enc")
	)
	
	suspend fun fetchCollectionSummary(): HttpResponse {
		return client.get(BandcampConstants.SUMMARY_URL) {
			header("Cookie", "identity=$token")
		}
	}
	
	suspend fun fetchCollectionItems(
		fanId: Long,
		itemCount: Int
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
	
	suspend fun fetchUserPage(
		username: String
	): HttpResponse {
		return client.get("${BandcampConstants.BASE_URL}/${username}") {
			header("Cookie", "identity=$token")
		}
	}
	
	suspend fun fetchStreamableUrl(
		rawUrl: String
	): HttpResponse {
		return client.get(rawUrl) {
			header("Cookie", "identity=$token")
		}
	}
}