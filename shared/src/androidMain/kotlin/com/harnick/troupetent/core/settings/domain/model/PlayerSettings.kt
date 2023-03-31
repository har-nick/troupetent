package com.harnick.troupetent.core.settings.domain.model

import kotlinx.serialization.Serializable

@Serializable
actual data class PlayerSettings(
    val gaplessPlaybackEnabled: Boolean = true
) : SettingsCollection
