package com.harnick.troupetent.core.settings.data.repository

import app.cash.sqldelight.coroutines.asFlow
import com.harnick.troupetent.core.settings.domain.model.*
import com.harnick.troupetent.core.settings.domain.repository.SettingsRepo
import com.harnick.troupetent.database.PersistentStorage
import kotlin.reflect.KClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@Inject
class SettingsRepoImpl(private val db: PersistentStorage) : SettingsRepo {
    private val queries = db.settings_tableQueries

    override fun createSettingsInstance() {
        queries.createFirstInstance(
            DownloadSettings(),
            GeneralSettings(),
            NotificationSettings(),
            PlayerSettings(),
            ThemeSettings()
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : SettingsCollection> getCollectionAsFlow(
        settingsType: KClass<T>
    ): Flow<SettingsCollection?> {
        return queries
            .getAllSettings()
            .asFlow()
            .map {
                when (settingsType) {
                    DownloadSettings::class -> it.executeAsOneOrNull()?.download_settings
                    GeneralSettings::class -> it.executeAsOneOrNull()?.general_settings
                    NotificationSettings::class -> it.executeAsOneOrNull()?.notification_settings
                    PlayerSettings::class -> it.executeAsOneOrNull()?.player_settings
                    ThemeSettings::class -> it.executeAsOneOrNull()?.theme_settings
                    else -> throw Exception("Attempted access of inaccessible or missing setting.")
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun toggleCollectionState(collection: ToggleableSettingsCollection) {
        val toggledCollection = collection.createToggled()

        updateCollection(toggledCollection)
    }

    override fun updateCollection(newSetting: SettingsCollection) {
        when (newSetting) {
            is DownloadSettings -> queries.updateDownloadSettings(newSetting)
            is GeneralSettings -> queries.updateGeneralSettings(newSetting)
            is NotificationSettings -> queries.updateNotificationSettings(newSetting)
            is PlayerSettings -> queries.updatePlayerSettings(newSetting)
            is ThemeSettings -> queries.updateThemeSettings(newSetting)
        }
    }
}
