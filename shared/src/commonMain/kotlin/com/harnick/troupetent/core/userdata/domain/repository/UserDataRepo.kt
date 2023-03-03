package com.harnick.troupetent.core.userdata.domain.repository

import com.harnick.troupetent.core.userdata.domain.model.BandcampUserData

interface UserDataRepo {
    fun isLoggedIn(): Flow<Boolean>

    fun loadUserData(): Flow<BandcampUserData>

    fun loadEncryptedToken(): Flow<Pair<ByteArray, ByteArray>>

    suspend fun updateUserData(userData: BandcampUserData)
}