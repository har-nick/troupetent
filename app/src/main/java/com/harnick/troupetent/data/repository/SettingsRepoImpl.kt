package com.harnick.troupetent.data.repository

import com.harnick.troupetent.data.model.room.settings.SettingsDao
import com.harnick.troupetent.domain.model.AppSettings
import com.harnick.troupetent.domain.repository.SettingsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepoImpl @Inject constructor(
	private val dao: SettingsDao
): SettingsRepo {
	override fun loadSettings(): Flow<AppSettings> = dao.loadSettings()
	
	override suspend fun updateSettings(appSettings: AppSettings) = dao.updateSettings(appSettings)
}