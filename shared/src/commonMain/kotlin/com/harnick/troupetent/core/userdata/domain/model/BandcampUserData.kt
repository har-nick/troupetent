package com.harnick.troupetent.core.userdata.domain.model

data class BandcampUserData(
    val profilePictureId: Long,
    val username: String,
    val userToken: Pair<ByteArray, ByteArray>
)
