package com.harnick.troupetent.data.model.room.settings

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harnick.troupetent.domain.model.AppSettings
import com.harnick.troupetent.domain.room_type_converters.SettingsConverters

@Database(entities = [AppSettings::class], version = 1)
@TypeConverters(SettingsConverters::class)
abstract class SettingsDatabase : RoomDatabase() {
	abstract val settingsDao: SettingsDao
	
	companion object {
		const val DB_NAME = "settings_db"
	}
}