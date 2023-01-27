package com.harnick.troupetent.core.user_data.data.model.room.bandcamp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harnick.troupetent.core.user_data.domain.model.BandcampUserData
import kotlinx.coroutines.flow.Flow

@Dao
interface BandcampUserDataDao {
	
	@Query("SELECT * FROM BandcampUserData")
	fun loadUserData(): Flow<BandcampUserData?>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun updateBandcampUserData(bandcampUserData: BandcampUserData)
}