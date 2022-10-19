package com.harnick.troupetent.domain.model

// TODO: IMAGE SIZING, CARD SIZING, CUSTOM PFP

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
	val appTheme: AppTheme = AppTheme.AUTO,
	val dataSaverMode: Boolean = false,
	val displayLanguage: Language = Language.ENG,
	val offlineMode: Boolean = false,
	val profilePicture: ProfilePicture = ProfilePicture.DEFAULT
)

@Serializable
enum class AppTheme(val displayName: String) {
	AUTO("System Theme"),
	LIGHT("Light"),
	DARK("Dark"),
	AMOLED("AMOLED"),
	YOU_LIGHT("Material You (Light)"),
	YOU_DARK("Material You (Dark)")
}

@Serializable
enum class Language(val displayName: String) {
	ENG("English (International)"),
	US("English (United States)")
}

@Serializable
enum class ProfilePicture(val displayName: String) {
	//	CUSTOM("Display a custom profile picture"),
	DEFAULT("Use your Bandcamp profile picture"),
	NONE("Do not display a profile picture")
}