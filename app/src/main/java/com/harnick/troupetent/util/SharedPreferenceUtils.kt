package com.harnick.troupetent.util

import android.content.Context
import android.content.SharedPreferences

// I remember seeing a bug with SharedPrefs not using the application context
// Not sure if true, but will only launch on the Main Activity with application context to be safe

object SharedPreferenceUtils {
  private lateinit var prefs: SharedPreferences
  private lateinit var editor: SharedPreferences.Editor
  
  fun init(context: Context) {
	prefs = context.getSharedPreferences("com.harnick.troupetent.settings", Context.MODE_PRIVATE)
	editor = prefs.edit()
  }
  
  fun read(key: String, value: Any): Any? {
	return when (value) {
	  is String -> prefs.getString(key, value)
	  is Boolean -> prefs.getBoolean(key, value)
	  is Int -> prefs.getInt(key, value)
	  is Float -> prefs.getFloat(key, value)
	  else -> throw UnsupportedOperationException("Value type cannot be read")
	}
  }
  
  fun write(key: String, value: Any) {
	when (value) {
	  is String -> editor.putString(key, value)
	  is Boolean -> editor.putBoolean(key, value)
	  is Int -> editor.putInt(key, value)
	  is Float -> editor.putFloat(key, value)
	  else -> throw UnsupportedOperationException("Value type cannot be written")
	}
	editor.apply()
  }
}