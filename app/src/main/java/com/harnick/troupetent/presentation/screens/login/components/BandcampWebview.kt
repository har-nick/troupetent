package com.harnick.troupetent.presentation.screens.login.components

import android.content.Context
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.harnick.troupetent.common.util.findActivity
import com.harnick.troupetent.presentation.screens.login.LoginEvent
import com.harnick.troupetent.presentation.screens.login.LoginViewModel

private class BandcampWebviewClient(
	private val viewModel: LoginViewModel,
	private val localContext: Context
) : AccompanistWebViewClient() {
	private val cookieManager: CookieManager = CookieManager.getInstance()
	
	var firstLoad = true
	
	override fun onPageFinished(view: WebView?, url: String?) {
		super.onPageFinished(view, url)
		
		if (firstLoad) {
			cookieManager.removeAllCookies(null)
			cookieManager.setAcceptThirdPartyCookies(view, false)
			firstLoad = false
		} else {
			if (url != null) {
				if (url.startsWith("https://bandcamp.com")) {
					val cookies = cookieManager.getCookie(url)
					
					val parsedCookies = cookies.split(";").associate {
						val (left, right) = it.split("=")
						left to right
					}.mapKeys { it.key.trim() }
					
					parsedCookies.forEach { cookie ->
						if (cookie.key == "identity") {
							viewModel.onEvent(LoginEvent.TokenFound(localContext, cookie.value))
						}
					}
				}
			}
		}
	}
}

@Composable
fun BandcampWebview(
	viewModel: LoginViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	val activity = context.findActivity()
	val intent = activity?.intent
	val loginUrl = intent?.data ?: "https://bandcamp.com/login"
	
	val webViewState = rememberWebViewState(loginUrl.toString())
	val webViewNavigator = rememberWebViewNavigator()
	val webViewClient = remember { BandcampWebviewClient(viewModel, context) }
	
	WebView(
		webViewState, Modifier.fillMaxSize(), navigator = webViewNavigator, onCreated = { webView ->
			webView.settings.javaScriptEnabled = true
		}, client = webViewClient
	)
}