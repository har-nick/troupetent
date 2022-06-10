package com.harnick.troupetent

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.harnick.troupetent.ui.theme.TroupetentTheme
import com.harnick.troupetent.util.CryptoUtils
import com.harnick.troupetent.util.RunUtils
import com.harnick.troupetent.util.SharedPreferenceUtils

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	SharedPreferenceUtils.init(applicationContext)
	RunUtils()
	CryptoUtils()
	WindowCompat.setDecorFitsSystemWindows(window, false)
	setContent {
	  TroupetentTheme {
		Navigation()
	  }
	}
  }
}