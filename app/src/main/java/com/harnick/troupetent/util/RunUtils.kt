package com.harnick.troupetent.util

import com.harnick.troupetent.BuildConfig

class RunUtils {
  init {
	if (checkIsFirstRun()) {
	  println("TT: FIRST RUN")
	  SettingsUtils.saveDefaultSettings()
	} else if (checkUpgradeRequired()) {
	  println("TT: NOT FIRST RUN")
//			TODO: SET UP SETTINGS MERGES
	}
  }
  
  private fun checkIsFirstRun(): Boolean {
	return SharedPreferenceUtils.read("firstRun", true) as Boolean
  }
  
  private fun checkUpgradeRequired(): Boolean {
	val localVer = BuildConfig.VERSION_CODE
	val savedVer = SharedPreferenceUtils.read(
	  "savedVer",
	  0
	) // BuildConfig ver cannot ever be 0, so considered fallback
	
	return localVer != savedVer
  }
}
