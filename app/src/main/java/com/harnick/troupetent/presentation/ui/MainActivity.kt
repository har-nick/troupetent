package com.harnick.troupetent.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.harnick.troupetent.presentation.ui.theme.TroupetentTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	setContent {
	  TroupetentTheme {

	  }
	}
  }
}