package com.harnick.troupetent.library.domain.use_cases

import com.harnick.troupetent.library.data.BandcampLibraryApi
import javax.inject.Inject

class GetBandcampStreamUrl @Inject constructor(
	private val bandcampLibraryApi: BandcampLibraryApi
) {
	suspend operator fun invoke(rawUrl: String, token: String): String {
		val response = bandcampLibraryApi.fetchStreamableUrl(rawUrl, token)
		
		return ""
	}
}