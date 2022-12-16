package com.harnick.troupetent.presentation.screens.login.components

import android.content.Context
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.harnick.troupetent.presentation.screens.login.LoginEvent
import com.harnick.troupetent.presentation.screens.login.LoginViewModel

private class BandcampWebviewClient(
	private val viewModel: LoginViewModel,
	private val setWebViewVisibility: (Boolean) -> (Unit),
	private val localContext: Context
) : AccompanistWebViewClient() {
	private val cookieManager: CookieManager = CookieManager.getInstance()
	
	override fun onPageFinished(view: WebView?, url: String?) {
		super.onPageFinished(view, url!!)
		
		setWebViewVisibility(true)
		
		if (url.startsWith("https://bandcamp.com")) {
			val cookies = cookieManager.getCookie(url)
			
			val parsedCookies = cookies.split(";").associate { rawCookie ->
				val (name, value) = rawCookie.split("=")
				name to value
			}.mapKeys { splitCookie -> splitCookie.key.trim() }
			
			for (cookie in parsedCookies) {
				if (cookie.key == "identity") {
					viewModel.onEvent(LoginEvent.TokenFound(localContext, cookie.value))
					break
				}
			}
		}
	}
}

@Composable
fun BandcampWebview(
	loginUrl: String,
	viewModel: LoginViewModel
) {
	val context = LocalContext.current
	
	var webViewIsVisible by remember { mutableStateOf(false) }
	val webViewAlpha: Float by animateFloatAsState(
		if (webViewIsVisible) 1f else 0f,
		tween(300, 100)                                                           // Animation sometimes skips. Delay is a workaround.
	)
	val webViewState = rememberWebViewState(loginUrl)
	val webViewNavigator = rememberWebViewNavigator()
	val webViewClient = BandcampWebviewClient(
		viewModel,
		{ webViewIsVisible = it },
		context
	)
	
	WebView(
		webViewState,
		Modifier
			.fillMaxSize()
			.alpha(webViewAlpha),
		navigator = webViewNavigator,
		onCreated = { webView -> webView.settings.javaScriptEnabled = true },
		client = webViewClient
	)
}