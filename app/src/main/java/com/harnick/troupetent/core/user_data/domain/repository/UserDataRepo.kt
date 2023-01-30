package com.harnick.troupetent.core.user_data.domain.repository

import com.harnick.troupetent.core.user_data.domain.model.BandcampUserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepo {
	fun getLoginStatus(): Flow<Boolean>
	
	fun loadUserData(): Flow<BandcampUserData?>
	
	fun loadEncryptedToken(): Flow<Pair<ByteArray, ByteArray>>
	
	suspend fun updateUserData(userData: BandcampUserData)
}