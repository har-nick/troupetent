package com.harnick.troupetent.ui.screens.webview

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.graphics.Bitmap
import android.provider.LiveFolders.INTENT
import android.webkit.CookieManager
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.google.accompanist.web.*
import com.harnick.troupetent.MainActivity
import com.harnick.troupetent.models.Screens
import com.harnick.troupetent.ui.theme.TroupetentTheme
import com.harnick.troupetent.util.CryptoUtils


@Composable
fun LoginWebView(navController: NavController) {
  TroupetentTheme {
	Column {
	  val url = "https://bandcamp.com/login"
	  val state = rememberWebViewState(url = url)
	  val navigator = rememberWebViewNavigator()
	  val context = LocalContext.current
	  val loadingState = state.loadingState

	  if (loadingState is LoadingState.Loading) {
		LinearProgressIndicator(
		  progress = loadingState.progress,
		  modifier = Modifier.fillMaxWidth()
		)
	  }

	  val webClient = remember {
		object : AccompanistWebViewClient() {
		  override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
			super.onPageFinished(view, url)

			if (url != null) {

			  if (url.startsWith("https://bandcamp.com")) {
				if (url != "https://bandcamp.com/login") {
				  val cookies = CookieManager.getInstance().getCookie(url)
				  val identityStartIndex: Int = cookies.indexOf("identity") + 9
				  val identityEndingIndex: Int = cookies.indexOf("js_logged_in") - 2
				  val substring =
					cookies.subSequence(identityStartIndex, identityEndingIndex).toString()

				  CryptoUtils.writeToEncryptedFile(
					context,
					"token",
					substring
				  )

				  navController.navigate(Screens.LibraryScreen.route) {
					popUpTo(0)
				  }

				  Toast.makeText(context, "Token captured.", Toast.LENGTH_SHORT).show()
				}
			  } else if (url != "https://bandcamp.com/login") {
				Toast.makeText(context, "Token not captured.", Toast.LENGTH_SHORT).show()
			  }
			}
		  }
		}
	  }

	  WebView(
		state = state,
		navigator = navigator,
		onCreated = { webView ->
		  webView.settings.javaScriptEnabled = true
		},
		client = webClient
	  )
	}
  }
}