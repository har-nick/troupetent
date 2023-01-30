package com.harnick.troupetent.core.user_data.data.local.bandcamp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harnick.troupetent.core.user_data.domain.model.BandcampUserData
import com.harnick.troupetent.core.user_data.domain.model.UserDataConverters

@Database(entities = [BandcampUserData::class], version = 1)
@TypeConverters(UserDataConverters::class)
abstract class BandcampUserDataDatabase : RoomDatabase() {
	abstract val bandcampUserDataDao: BandcampUserDataDao
	
	companion object {
		const val DB_NAME = "bandcamp_user_data_db"
	}
}