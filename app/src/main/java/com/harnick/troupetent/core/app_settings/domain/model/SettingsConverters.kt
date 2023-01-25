package com.harnick.troupetent.core.app_settings.domain.model

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SettingsConverters {
	@TypeConverter
	fun fromAppTheme(appTheme: AppTheme): String {
		return Json.encodeToString(appTheme)
	}
	
	@TypeConverter
	fun toAppTheme(serializedAppTheme: String): AppTheme {
		return Json.decodeFromString(serializedAppTheme)
	}
	
	@TypeConverter
	fun fromDisplayLanguage(displayLanguage: DisplayLanguage): String {
		return Json.encodeToString(displayLanguage)
	}
	
	@TypeConverter
	fun toDisplayLanguage(serializedDisplayLanguage: String): DisplayLanguage {
		return Json.decodeFromString(serializedDisplayLanguage)
	}
	
	@TypeConverter
	fun fromBandcampEncoder(bandcampEncoder: BandcampEncoder): String {
		return Json.encodeToString(bandcampEncoder)
	}
	
	@TypeConverter
	fun toBandcampEncoder(serializedBandcampEncoder: String): BandcampEncoder {
		return Json.decodeFromString(serializedBandcampEncoder)
	}
	
	@TypeConverter
	fun fromProfilePicture(profilePicture: ProfilePicture): String {
		return Json.encodeToString(profilePicture)
	}
	
	@TypeConverter
	fun fromProfilePicture(serializedProfilePicture: String): ProfilePicture {
		return Json.decodeFromString(serializedProfilePicture)
	}
}