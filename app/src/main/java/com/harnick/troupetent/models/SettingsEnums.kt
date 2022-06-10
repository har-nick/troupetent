package com.harnick.troupetent.models

class SettingsEnums {
  enum class DownloadQuality(val displayName: String) {
	AAC("AAC"),
	AIFF("AIFF"),
	ALAC("ALAC"),
	FLAC("FLAC"),
	MP3_320("MP3 320"),
	MP3_V0("MP3 V0"),
	OGG("Ogg Vorbis"),
	WAV("WAV"),
  }

  enum class Languages(val nativeName: String) {
	ENG("English"),
  }

  //TODO: SET UP CUSTOM PFP SUPPORT
  enum class ProfilePictureSource(val displayName: String) {
	BANDCAMP("Use Bandcamp default"),

	//	CUSTOM("Use a custom profile picture"),

	NONE("Disabled"),
  }

  enum class SyncIntervals(val displayName: String) {
	H1("Every hour"),
	H6("Every six hours"),
	H12("Every twelve hours"),
	D1("Daily"),
	W1("Weekly"),
  }

  enum class Themes(val displayName: String) {
	SYSTEM("Follow system theme"),
	LIGHT("Light Mode"),
	DARK("Dark Mode"),
	AMOLED("AMOLED")
  }
}