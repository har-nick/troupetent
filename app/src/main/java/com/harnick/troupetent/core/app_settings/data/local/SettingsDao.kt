package com.harnick.troupetent.core.app_settings.data.local

import androidx.room.*
import com.harnick.troupetent.core.app_settings.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
	
	@Query("SELECT * FROM appsettings")
	fun loadSettings(): Flow<AppSettings>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun updateSettings(appSettings: AppSettings)
}