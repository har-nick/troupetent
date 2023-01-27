package com.harnick.troupetent.core.app_settings.data.repository

import com.harnick.troupetent.core.app_settings.data.model.room.SettingsDao
import com.harnick.troupetent.core.app_settings.domain.model.AppSettings
import com.harnick.troupetent.core.app_settings.domain.repository.SettingsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

class SettingsRepoImpl @Inject constructor(
	private val settingsDao: SettingsDao
) : SettingsRepo {
	override fun loadSettings(): Flow<AppSettings> = settingsDao.loadSettings().filterNotNull()
	
	override suspend fun updateSettings(appSettings: AppSettings) =
		settingsDao.updateSettings(appSettings)
}