package com.harnick.troupetent.core.user_data.data.repository

import com.harnick.troupetent.core.user_data.data.model.room.bandcamp.BandcampUserDataDao
import com.harnick.troupetent.core.user_data.domain.model.BandcampUserData
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepoImpl @Inject constructor(
	private val userDataDao: BandcampUserDataDao
) : UserDataRepo {
	override fun loadUserData(): Flow<BandcampUserData> = userDataDao.loadUserData()
	
	override suspend fun updateUserData(userData: BandcampUserData) =
		userDataDao.updateBandcampUserData(userData)
}