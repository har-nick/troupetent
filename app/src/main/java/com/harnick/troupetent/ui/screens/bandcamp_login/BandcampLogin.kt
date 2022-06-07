package com.harnick.troupetent.ui.screens.bandcamp_login

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun loadWebview(url: String) {
  AndroidView(
	factory = {
	  WebView(it).apply {
		webViewClient = object : WebViewClient() {
		  override fun shouldOverrideUrlLoading(
			view: WebView?,
			url: String
		  ): Boolean {
			return !url.startsWith("https://bandcamp.com")
		  }
		}
		
		loadUrl(url)
		settings.javaScriptEnabled = true
	  }
	}, update = {
	  it.loadUrl(url)
	})
}