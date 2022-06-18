package com.harnick.troupetent.data.remote

import com.harnick.troupetent.data.remote.dto.bandcamp.AlbumEntity
import com.harnick.troupetent.data.remote.dto.bandcamp.CollectionSummaryEntity
import com.harnick.troupetent.domain.model.Track
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

interface BandcampApi {
  companion object {
	private const val BASE_URL = "https://bandcamp.com"
	const val COLLECTION_SUMMARY = "$BASE_URL/api/fan/2/collection_summary"
	const val COLLECTION_ITEMS = "$BASE_URL/api/fancollection/1/collection_items"
	const val OLDER_THAN_TOKEN =
	  4102444800 // Web requests require a Unix timestamp. Why? Idk. Set to 2100 A.D.
	val client = OkHttpClient()
  }

  suspend fun getCollectionSummary(token: String): CollectionSummaryEntity {
	val request = Request.Builder()
	  .url(BandcampApi.COLLECTION_SUMMARY)
	  .header("identity", token)
	  .build()


  }

  suspend fun getCollectionItems(token: String): List<AlbumEntity> {
	val postBody = FormBody.Builder()
	  .add("fan_id", "")
	val request = Request.Builder()
	  .url(BandcampApi.COLLECTION_ITEMS)
	  .addHeader("identity", token)
	  .post()
	  .build()
  }

  suspend fun getAlbumTracks(token: String, albumId: Long): List<Track>
}