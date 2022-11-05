package com.harnick.troupetent.domain.model

// TODO: IMAGE SIZING, CARD SIZING, CUSTOM PFP

import android.os.Build
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
	val appTheme: AppTheme = if (Build.VERSION.SDK_INT < 31) AppTheme.AUTO else AppTheme.YOU_AUTO,
	val dataSaverMode: Boolean = false,
	val displayLanguage: Language = Language.ENG,
	val bandcampDownloadEncoding: BandcampEncoder = BandcampEncoder.MP3320,
	val offlineMode: Boolean = false,
	val profilePicture: ProfilePicture = ProfilePicture.DEFAULT
)

@Serializable
enum class AppTheme(val displayName: String) {
	AUTO("Follow system theme"),
	LIGHT("Light"),
	DARK("Dark"),
	AMOLED("AMOLED"),
	YOU_AUTO("Material You - Follow system theme"),
	YOU_LIGHT("Material You - Light"),
	YOU_DARK("Material You - Dark")
}

@Serializable
enum class BandcampEncoder(val apiName: String, val displayName: String) {
	AAC("aac-hi", "AAC"),
	AIFF("aiff-lossless", "AIFF"),
	ALAC("alac", "ALAC"),
	FLAC("flac", "FLAC"),
	MP3320("mp3-320", "MP3 (320kbps)"),
	MP3V0("mp3-v0", "MP3 (VBR V0)"),
	OGG("vorbis", "Ogg Vorbis"),
	WAV("wav", "WAV")
}

@Serializable
enum class Language(val displayName: String) {
	ENG("English - International"),
	US("English - United States")
}

@Serializable
enum class ProfilePicture(val displayName: String) {
	//	CUSTOM("Display a custom profile picture"),
	DEFAULT("Use your Bandcamp profile picture"),
	NONE("Do not display a profile picture")
}