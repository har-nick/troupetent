package com.harnick.troupetent.core.user_data.data.repository

import com.harnick.troupetent.core.user_data.data.model.room.bandcamp.BandcampUserDataDao
import com.harnick.troupetent.core.user_data.domain.model.BandcampUserData
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserDataRepoImpl @Inject constructor(
	private val userDataDao: BandcampUserDataDao
) : UserDataRepo {
	override fun getLoginStatus(): Flow<Boolean> = flow {
		userDataDao.loadUserData()
			.flowOn(Dispatchers.IO)
			.onEach { data ->
				emit(data?.userToken != null)
			}
			.collect()
	}
	
	override fun loadUserData(): Flow<BandcampUserData> {
		return userDataDao.loadUserData()
			.filterNotNull()
			.flowOn(Dispatchers.IO)
	}
	
	
	override suspend fun updateUserData(userData: BandcampUserData) =
		userDataDao.updateBandcampUserData(userData)
}