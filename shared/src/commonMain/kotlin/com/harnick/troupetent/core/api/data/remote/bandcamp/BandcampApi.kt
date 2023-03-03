package com.harnick.troupetent.core.api.data.remote.bandcamp

class BandcampApi constructor(
    private val client: Any
) {
    suspend fun fetchCollectionSummary(token: String) {}

    suspend fun fetchCollectionItems(
        fanId: Long,
        itemCount: Long,
        token: String
    ) {}

    suspend fun fetchUserPage(username: String) {}
}