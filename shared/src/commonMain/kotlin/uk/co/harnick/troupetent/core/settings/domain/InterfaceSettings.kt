package uk.co.harnick.troupetent.core.settings.domain

import uk.co.harnick.troupetent.core.ui.theme.domain.model.Theme
import uk.co.harnick.troupetent.core.util.Language

expect class InterfaceSettings {
    object Defaults

    val language: LanguageSetting
    val theme: ThemeSetting
}

expect class ThemeSetting : Setting<Theme>

class LanguageSetting(override val value: Language) : Setting<Language> {
    override val title = "Display Language"
    override val description = null
}