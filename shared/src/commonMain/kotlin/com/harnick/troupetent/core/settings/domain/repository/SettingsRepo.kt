package com.harnick.troupetent.core.settings.domain.repository

import com.harnick.troupetent.core.settings.domain.model.SettingsCollection
import com.harnick.troupetent.core.settings.domain.model.ToggleableSettingsCollection
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

interface SettingsRepo {
    fun createSettingsInstance()

    fun <T : SettingsCollection> getCollectionAsFlow(settingsType: KClass<T>): Flow<SettingsCollection?>

    suspend fun toggleCollectionState(collection: ToggleableSettingsCollection)

    fun updateCollection(newSetting: SettingsCollection)
}