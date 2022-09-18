package com.harnick.troupetent.domain.use_cases

import com.harnick.troupetent.data.remote.bandcamp.BandcampApi
import javax.inject.Inject

class GetBandcampStreamUrl @Inject constructor(
	private val api: BandcampApi
) {
	suspend operator fun invoke(
		rawUrl: String
	): String {
		val response = api.fetchStreamableUrl(rawUrl)
		
		print(response.status)
		return ""
	}
}