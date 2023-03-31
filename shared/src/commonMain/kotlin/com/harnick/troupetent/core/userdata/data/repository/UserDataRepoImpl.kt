package com.harnick.troupetent.core.userdata.data.repository

import com.harnick.troupetent.core.userdata.domain.model.BandcampUserData
import com.harnick.troupetent.core.userdata.domain.repository.UserDataRepo
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class UserDataRepoImpl() : UserDataRepo {
    override fun loadBandcampUserData(): Flow<BandcampUserData?> {
        TODO("Not yet implemented")
    }

    override fun updateBandcampUserData(userData: BandcampUserData) {
        TODO("Not yet implemented")
    }

    override fun loadEncryptedToken(): Flow<Pair<ByteArray, ByteArray>?> {
        TODO("Not yet implemented")
    }

    override fun loadLoginStatus(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

}