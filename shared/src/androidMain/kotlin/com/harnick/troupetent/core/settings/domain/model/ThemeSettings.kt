package com.harnick.troupetent.core.settings.domain.model

import android.os.Build

actual data class ThemeSettings(
    val materialYouEnabled: Boolean = Build.VERSION.SDK_INT > 30,
    val theme: AndroidAppTheme.AUTO
)

sealed class AndroidAppTheme(val displayName: String) {
    object AUTO : AndroidAppTheme("Follow system theme")
    object LIGHT : AndroidAppTheme("Light")
    object DARK : AndroidAppTheme("Dark")
}
