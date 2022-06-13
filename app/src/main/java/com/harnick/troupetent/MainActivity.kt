package com.harnick.troupetent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.harnick.troupetent.ui.theme.TroupetentTheme
import com.harnick.troupetent.util.CryptoUtils
import com.harnick.troupetent.util.RunUtils
import com.harnick.troupetent.util.SharedPreferenceUtils
import java.io.File

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	SharedPreferenceUtils.init(applicationContext)
	RunUtils()

	val token = if (File(applicationContext.filesDir, "token").isFile) {
	  CryptoUtils.decryptFile(applicationContext, "token")
	} else {
		false
	}

	println(token)

	WindowCompat.setDecorFitsSystemWindows(window, false)
	setContent {
	  TroupetentTheme {
		Navigation(token)
	  }
	}
  }
}