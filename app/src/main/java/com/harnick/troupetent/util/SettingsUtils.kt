package com.harnick.troupetent.util

import com.harnick.troupetent.models.SettingsModel
import com.harnick.troupetent.util.GenericUtils.setPropertyValue
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties

object SettingsUtils {
  private val settings = SettingsModel()

  fun saveDefaultSettings() {
	for (setting in SettingsModel::class.memberProperties) {
	  val settingVal = setting.get(settings)

	  if (setting.name == "firstRun") {
		SharedPreferenceUtils.write(setting.name, false)
	  } else if (settingVal!!::class.isSubclassOf(Enum::class)) {
		SharedPreferenceUtils.write(
		  setting.name,
		  setting.get(settings).toString()
		) // ENUMS ARE SAVED AS STRING AND CONVERTED AFTER RETRIEVING
	  } else {
		SharedPreferenceUtils.write(setting.name, setting.get(settings)!!)
	  }
	}

	SharedPreferenceUtils.write(
	  "isFirstRun",
	  false
	) // SETTINGS ARE NOW SAVED; FIRSTRUN VAR CAN BE TOGGLED
  }

  fun fromPrefs(): SettingsModel {
	for (setting in SettingsModel::class.memberProperties) {
	  val pref = SharedPreferenceUtils.read(setting.name, "Not set")

	  if (pref !== "Not set") {
		println("Found saved setting")
		settings.setPropertyValue(setting.name, pref!!)
	  }
	}
	return settings
  }
}