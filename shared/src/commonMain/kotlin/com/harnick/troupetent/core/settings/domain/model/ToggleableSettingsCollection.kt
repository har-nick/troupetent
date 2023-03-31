package com.harnick.troupetent.core.settings.domain.model

interface ToggleableSettingsCollection : SettingsCollection {
    val isEnabled: Boolean
    fun createToggled(): ToggleableSettingsCollection
}