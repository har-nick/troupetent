package com.harnick.troupetent.core.settings.domain.model

data class MainSettings(
    val dataSaverEnabled: Boolean = false,
    val displayLanguage: DisplayLanguage = DisplayLanguage.ENG,
    val nickname: String? = null,
    val offlineModeEnabled: Boolean = false
)

sealed class DisplayLanguage(val displayName: String) : Setting(
    description = null,
    title = "Display Language"
) {
    object ENG : DisplayLanguage("English (International)")
    object US : DisplayLanguage("English (American)")
}