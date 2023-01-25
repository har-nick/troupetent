package com.harnick.troupetent.core.app_settings.domain.repository

import com.harnick.troupetent.core.app_settings.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepo {
	fun loadSettings(): Flow<AppSettings>
	
	suspend fun updateSettings(appSettings: AppSettings)
}