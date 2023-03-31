package com.harnick.troupetent.core.settings.domain.model

import android.os.Build
import com.harnick.troupetent.core.settings.domain.model.ThemeSettings.DefaultValues.defaultMaterialYouEnabled
import com.harnick.troupetent.core.settings.domain.model.ThemeSettings.DefaultValues.defaultTheme
import kotlinx.serialization.Serializable

@Serializable
actual data class ThemeSettings(
    val materialYouEnabled: MaterialYouEnabledSetting = defaultMaterialYouEnabled,
    val theme: ThemeSetting = defaultTheme
) : SettingsCollection {
    private object DefaultValues {
        val defaultMaterialYouEnabled = MaterialYouEnabledSetting(Build.VERSION.SDK_INT > 30)
        val defaultTheme = ThemeSetting(AndroidAppTheme.AUTO)
    }
}

@Serializable
class MaterialYouEnabledSetting(override val value: Boolean) : Setting<Boolean> {
    override val title = "Enable Material You"
    override val description = ""
}

@Serializable
class ThemeSetting(override val value: AndroidAppTheme) : Setting<AndroidAppTheme> {
    override val title = "Theme"
    override val description: String = ""
}

@Serializable
sealed class AndroidAppTheme(val displayName: String) {
    object AUTO : AndroidAppTheme("Follow system theme")
    object AMOLED : AndroidAppTheme("AMOLED")
    object LIGHT : AndroidAppTheme("Light")
    object DARK : AndroidAppTheme("Dark")
}
