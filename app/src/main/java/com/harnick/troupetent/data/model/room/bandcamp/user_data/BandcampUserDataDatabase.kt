package com.harnick.troupetent.data.model.room.bandcamp.user_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harnick.troupetent.domain.model.bandcamp.BandcampUserData

@Database(entities = [BandcampUserData::class], version = 1)
abstract class BandcampUserDataDatabase : RoomDatabase() {
	abstract val bandcampUserDataDao: BandcampUserDataDao
	
	companion object {
		const val DB_NAME = "bandcamp_user_data_db"
	}
}