package com.harnick.troupetent.core.settings.domain.model

import Localisation
import com.harnick.troupetent.core.settings.domain.model.GeneralSettings.DefaultValues.defaultDisplayLanguage
import com.harnick.troupetent.core.settings.domain.model.GeneralSettings.DefaultValues.defaultNickname
import com.harnick.troupetent.core.settings.domain.model.GeneralSettings.DefaultValues.defaultOfflineModeState
import com.harnick.troupetent.core.settings.domain.model.GeneralSettings.DefaultValues.defaultSyncRate
import kotlinx.serialization.Serializable

@Serializable
data class GeneralSettings(
    val displayLanguage: DisplayLanguageSetting = defaultDisplayLanguage,
    val nickname: NicknameSetting = defaultNickname,
    val offlineModeEnabled: OfflineModeSetting = defaultOfflineModeState,
    val syncRate: SyncRateSetting = defaultSyncRate
) : SettingsCollection {
    private object DefaultValues {
        val defaultDisplayLanguage = DisplayLanguageSetting(DisplayLanguage.ENG)
        val defaultNickname = NicknameSetting("")
        val defaultOfflineModeState = OfflineModeSetting(false)
        val defaultSyncRate = SyncRateSetting(SyncInterval.H1)
    }
}

@Serializable
class DisplayLanguageSetting(override val value: DisplayLanguage) : Setting<DisplayLanguage> {
    override val title = Localisation.settingDisplayLanguageTitle.toString()
    override val description = ""
}

@Serializable
class NicknameSetting(override val value: String) : Setting<String> {
    override val title = Localisation.settingNicknameTitle.toString()
    override val description = Localisation.settingNicknameDescription.toString()
}

@Serializable
class OfflineModeSetting(override val value: Boolean) : Setting<Boolean> {
    override val title = Localisation.settingOfflineModeTitle.toString()
    override val description = Localisation.settingOfflineModeDescription.toString()
}

@Serializable
class SyncRateSetting(override val value: SyncInterval) : Setting<SyncInterval> {
    override val title = Localisation.settingSyncRateTitle.toString()
    override val description = Localisation.settingSyncRateDescription.toString()
}

@Serializable
sealed class DisplayLanguage(val optionName: String, val i18nName: String) {
    object ENG : DisplayLanguage("English (British)", "en-gb")
}

@Serializable
sealed class SyncInterval(val timestampRepresentation: Long) {
    object H1 : SyncInterval(3600)
    object H6 : SyncInterval(21600)
    object H12 : SyncInterval(43200)
    object D1 : SyncInterval(86400)
    object D7 : SyncInterval(604800)
}
