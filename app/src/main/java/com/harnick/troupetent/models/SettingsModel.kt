package com.harnick.troupetent.models

import com.harnick.troupetent.BuildConfig

data class SettingsModel(
  var dataSaverMode: Boolean = false,
  var downloadQuality: SettingsEnums.DownloadQuality = SettingsEnums.DownloadQuality.OGG,
  var firstRun: Boolean = true,
  var gaplessPlayback: Boolean = true,
  var language: SettingsEnums.Languages = SettingsEnums.Languages.ENG,
  var nickname: String? = "NO NICKNAME",
  var requireUserAuth: Boolean = false,
  var monoAudio: Boolean = false,
  var offlineMode: Boolean = false,
  var profilePicture: SettingsEnums.ProfilePictureSource = SettingsEnums.ProfilePictureSource.BANDCAMP,
  var savedVer: Int = BuildConfig.VERSION_CODE,
  var showHidden: Boolean = true,
  var syncTimer: SettingsEnums.SyncIntervals = SettingsEnums.SyncIntervals.D1,
  var themeMode: SettingsEnums.Themes = SettingsEnums.Themes.LIGHT,
)