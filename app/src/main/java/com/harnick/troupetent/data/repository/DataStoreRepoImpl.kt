package com.harnick.troupetent.data.repository

import android.content.Context
import androidx.datastore.dataStore
import com.harnick.troupetent.data.model.AppSettings
import com.harnick.troupetent.data.model.AppTheme
import com.harnick.troupetent.data.model.Language
import com.harnick.troupetent.domain.repository.DataStoreRepo
import com.harnick.troupetent.domain.serializers.AppSettingsSerializer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

class DataStoreRepoImpl @Inject constructor(
	@ApplicationContext private val appContext: Context
) : DataStoreRepo {

	override fun getSettings(): Flow<AppSettings> {
		return appContext.dataStore.data
	}

	override suspend fun setLanguage(language: Language) {
		appContext.dataStore.updateData {
			it.copy(displayLanguage = language)
		}
	}

	override suspend fun setAppTheme(appTheme: AppTheme) {
		appContext.dataStore.updateData {
			it.copy(appTheme = appTheme)
		}
	}

	override suspend fun setOfflineMode(offlineMode: Boolean) {
		appContext.dataStore.updateData {
			it.copy(offlineMode = offlineMode)
		}
	}

	override suspend fun setDataSaverMode(dataSaverMode: Boolean) {
		appContext.dataStore.updateData {
			it.copy(dataSaverMode = dataSaverMode)
		}
	}
}