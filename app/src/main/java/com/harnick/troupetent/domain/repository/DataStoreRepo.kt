package com.harnick.troupetent.domain.repository

import com.harnick.troupetent.domain.model.AppSettings
import com.harnick.troupetent.domain.model.AppTheme
import com.harnick.troupetent.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface DataStoreRepo {
	fun getSettings(): Flow<AppSettings>
	
	suspend fun setLanguage(language: Language)
	
	suspend fun setAppTheme(appTheme: AppTheme)
	
	suspend fun setOfflineMode(offlineMode: Boolean)
	
	suspend fun setDataSaverMode(dataSaverMode: Boolean)
}