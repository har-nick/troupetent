package com.harnick.troupetent.core.user_data.data.repository

import com.harnick.troupetent.core.user_data.data.local.bandcamp.BandcampUserDataDao
import com.harnick.troupetent.core.user_data.domain.model.BandcampUserData
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserDataRepoImpl @Inject constructor(
	private val userDataDao: BandcampUserDataDao
) : UserDataRepo {
	override fun getLoginStatus(): Flow<Boolean> = flow {
		loadUserData()
			.onEach { data ->
				emit(data?.userToken != null)
			}
			.collect()
	}
	
	override fun loadUserData(): Flow<BandcampUserData?> {
		return userDataDao.loadUserData()
			.flowOn(Dispatchers.IO)
	}
	
	override fun loadEncryptedToken(): Flow<Pair<ByteArray, ByteArray>> = flow {
		loadUserData()
			.filterNotNull()
			.onEach { data ->
				if (data.userToken != null) {
					emit(data.userToken)
				}
			}
			.collect()
	}
	
	override suspend fun updateUserData(userData: BandcampUserData) =
		userDataDao.updateBandcampUserData(userData)
}