package uk.co.harnick.troupetent.core.settings.domain

import android.os.Build
import uk.co.harnick.troupetent.core.ui.theme.domain.model.Theme
import uk.co.harnick.troupetent.core.ui.theme.domain.model.Theme.Auto
import uk.co.harnick.troupetent.core.util.Language.en_gb

actual class InterfaceSettings {
    actual object Defaults {
        val language = LanguageSetting(en_gb)
        val materialYou = MaterialYouSetting(Build.VERSION.SDK_INT > Build.VERSION_CODES.R)
        val theme = ThemeSetting(Auto)
    }

    actual val language = Defaults.language
    val materialYou = Defaults.materialYou
    actual val theme = Defaults.theme
}

class MaterialYouSetting(override val value: Boolean) : Setting<Boolean> {
    override val title = "Material You"
    override val description = null
}

actual class ThemeSetting(override val value: Theme) : Setting<Theme> {
    override val title = "Theme"
    override val description = null
}
