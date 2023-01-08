package com.harnick.troupetent.data.model.room.settings

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harnick.troupetent.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
	
	@Query("SELECT * FROM appsettings")
	fun loadSettings(): Flow<AppSettings>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun updateSettings(appSettings: AppSettings)
}