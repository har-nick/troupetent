package uk.co.harnick.troupetent.core.settings.domain

import uk.co.harnick.troupetent.core.ui.theme.domain.model.Theme
import uk.co.harnick.troupetent.core.ui.theme.domain.model.Theme.Auto
import uk.co.harnick.troupetent.core.util.Language
import uk.co.harnick.troupetent.core.util.Language.en_gb

actual class InterfaceSettings {
    actual object Defaults {
        val language = LanguageSetting(en_gb)
        val theme = ThemeSetting(Auto)
    }

    actual val language = Defaults.language
    actual val theme = Defaults.theme
}

actual class ThemeSetting(override val value: Theme) : Setting<Theme> {
    override val title = "Theme"
    override val description = null
}