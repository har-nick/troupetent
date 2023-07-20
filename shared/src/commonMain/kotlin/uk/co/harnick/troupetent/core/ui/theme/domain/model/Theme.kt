package uk.co.harnick.troupetent.core.ui.theme.domain.model

sealed class Theme {
    object AMOLED : Theme()
    object Auto : Theme()
    object Light : Theme()
    object Dark : Theme()
}
