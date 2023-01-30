package com.harnick.troupetent.core.app_settings.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harnick.troupetent.core.app_settings.domain.model.AppSettings
import com.harnick.troupetent.core.app_settings.domain.model.SettingsConverters

@Database(entities = [AppSettings::class], version = 1)
@TypeConverters(SettingsConverters::class)
abstract class SettingsDatabase : RoomDatabase() {
	abstract val settingsDao: SettingsDao
	
	companion object {
		const val DB_NAME = "settings_db"
	}
}