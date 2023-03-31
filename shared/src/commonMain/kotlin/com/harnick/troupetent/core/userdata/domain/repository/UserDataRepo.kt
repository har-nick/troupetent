package com.harnick.troupetent.core.userdata.domain.repository

import com.harnick.troupetent.core.userdata.domain.model.BandcampUserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepo {
    fun loadBandcampUserData(): Flow<BandcampUserData?>

    fun updateBandcampUserData(userData: BandcampUserData)

    fun loadEncryptedToken(): Flow<Pair<ByteArray, ByteArray>?>

    fun loadLoginStatus(): Flow<Boolean>
}