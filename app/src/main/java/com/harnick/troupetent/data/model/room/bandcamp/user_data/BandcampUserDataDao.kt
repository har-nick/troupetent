package com.harnick.troupetent.data.model.room.bandcamp.user_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harnick.troupetent.domain.model.bandcamp.BandcampUserData
import kotlinx.coroutines.flow.Flow

@Dao
interface BandcampUserDataDao {
	
	@Query("SELECT * FROM BandcampUserData")
	fun loadUserData(): Flow<BandcampUserData>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun updateBandcampUserData(bandcampUserData: BandcampUserData)
}