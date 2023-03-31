package com.harnick.troupetent.core.api.data.remote.bandcamp

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import me.tatarka.inject.annotations.Inject

@Inject
class BandcampApi(private val client: HttpClient) {

    // TODO: Write API functions

    companion object ApiConstants {
        const val BASE_URL = "https://bandcamp.com"
        const val SUMMARY_ENDPOINT = "$BASE_URL/api/fan/2/collection_summary"
        const val LIBRARY_ENDPOINT = "$BASE_URL/api/fancollection/1/collection_items"
        const val IMAGE_URL = "https://f4.bcbits.com/img"
    }

    suspend fun fetchCollectionSummary(token: String) {
        client.get(SUMMARY_ENDPOINT) {
            header("Cookie", token)
        }
    }

    suspend fun fetchCollectionItems(
        fanId: Long,
        itemCount: Long,
        token: String
    ) {
    }

    suspend fun fetchUserPage(username: String): String {
        return client.get("$BASE_URL/$username").bodyAsText()
    }
}