package com.harnick.troupetent.core.app_settings.domain.model

// TODO: IMAGE SIZING, CARD SIZING, CUSTOM PFP
// TODO: FIND BETTER ALTERNATIVE TO EXCESSIVE ANNOTATIONS

import android.os.Build
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class AppSettings(
	@PrimaryKey
	val savedTimestamp: Long = System.currentTimeMillis(),
	val appTheme: AppTheme = if (Build.VERSION.SDK_INT < 31) AppTheme.Auto else AppTheme.YouAuto,
	val dataSaverMode: Boolean = false,
	val displayLanguage: DisplayLanguage = DisplayLanguage.ENG,
	val bandcampDownloadEncoding: BandcampEncoder = BandcampEncoder.MP3320,
	val offlineMode: Boolean = false,
	val profilePicture: ProfilePicture = ProfilePicture.DEFAULT
)

@Serializable
abstract class Setting(
	val settingTitle: String,
	val settingDescription: String? = null
)

@Serializable
sealed class AppTheme(val displayName: String) : Setting(
	"Application Theme"
) {
	@Serializable
	object Auto : AppTheme("Follow system theme")
	@Serializable
	object Light : AppTheme("Light")
	@Serializable
	object Dark : AppTheme("Dark")
	@Serializable
	object AMOLED : AppTheme("AMOLED")
	@Serializable
	object YouAuto : AppTheme("Material You - Follow system theme")
	@Serializable
	object YouLight : AppTheme("Material You - Light")
	@Serializable
	object YouDark : AppTheme("Material You - Dark")
}

@Serializable
sealed class BandcampEncoder(val apiName: String, val displayName: String) : Setting(
	"Default Download Format"
) {
	@Serializable
	object AAC : BandcampEncoder("aac-hi", "AAC")
	@Serializable
	object AIFF : BandcampEncoder("aiff-lossless", "AIFF")
	@Serializable
	object ALAC : BandcampEncoder("alac", "ALAC")
	@Serializable
	object FLAC : BandcampEncoder("flac", "FLAC")
	@Serializable
	object MP3320 : BandcampEncoder("mp3-320", "MP3 (320kbps)")
	@Serializable
	object MP3V0 : BandcampEncoder("mp3-v0", "MP3 (VBR V0)")
	@Serializable
	object OGG : BandcampEncoder("vorbis", "Ogg Vorbis")
	@Serializable
	object WAV : BandcampEncoder("wav", "WAV")
}

@Serializable
sealed class DisplayLanguage(val displayName: String) : Setting(
	"Display Language"
) {
	@Serializable
	object ENG : DisplayLanguage("English - International")
	@Serializable
	object US : DisplayLanguage("English - United States")
}

@Serializable
sealed class ProfilePicture(val displayName: String) : Setting(
	"Profile Picture",
	"How your profile picture should be displayed"
) {
	//	CUSTOM("Display a custom profile picture"),
	@Serializable
	object DEFAULT : ProfilePicture("Use your Bandcamp profile picture")
	@Serializable
	object NONE : ProfilePicture("Do not display a profile picture")
}