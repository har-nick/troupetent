package com.harnick.troupetent.domain.repository

import com.harnick.troupetent.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepo {
	fun loadSettings(): Flow<AppSettings?>
	
	suspend fun updateSettings(appSettings: AppSettings)
}