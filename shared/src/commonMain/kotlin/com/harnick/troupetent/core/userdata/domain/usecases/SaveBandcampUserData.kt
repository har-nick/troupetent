package com.harnick.troupetent.core.userdata.domain.usecases

import com.harnick.troupetent.core.api.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.core.userdata.domain.repository.UserDataRepo

class SaveBandcampUserData constructor(
    private val bandcampApi: BandcampApi,
    private val encryptionRepo: Any,
    private val userDataRepo: UserDataRepo
) {
    operator fun invoke() {}
}