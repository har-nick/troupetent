package uk.co.harnick.troupetent.core.util

sealed class Language(val displayName: String) {
    object en_gb : Language("English (British)")
}
