package com.harnick.troupetent.authentification.presentation.screens.login.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.harnick.troupetent.authentification.domain.BandcampWebChromeClient
import com.harnick.troupetent.authentification.domain.BandcampWebViewClient
import com.harnick.troupetent.authentification.presentation.screens.login.LoginEvent

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BandcampWebView(
	startUrl: String,
	handleEvent: (event: LoginEvent) -> Unit,
	webViewIsVisible: Boolean
) {
	val localContext = LocalContext.current
	
	val webViewAlpha: Float by animateFloatAsState(
		if (webViewIsVisible) 1f else 0f,
		//	OnPageFinished != DOM painted. Delay just helps a bit.
		tween(300, 100)
	)
	val webViewState = rememberWebViewState(startUrl)
	val webViewNavigator = rememberWebViewNavigator()
	val webChromeClient = BandcampWebChromeClient(handleEvent)
	val webViewClient = remember {
		BandcampWebViewClient(handleEvent, localContext)
	}
	
	Box(
		Modifier
			.alpha(webViewAlpha)
			.fillMaxSize()
			.zIndex(1f)
	) {
		WebView(
			state = webViewState,
			modifier = Modifier.fillMaxSize(),
			captureBackPresses = false,
			navigator = webViewNavigator,
			onCreated = { webView ->
				webView.settings.javaScriptEnabled = true
				webView.addJavascriptInterface(
					webViewClient.BandcampWebViewInterface(),
					"TroupetentLoginInterface"
				)
				webViewClient.clientView = webView
			},
			client = webViewClient,
			chromeClient = webChromeClient
		)
		
		FloatingActionButton(
			onClick = { webViewClient.goHome() },
			modifier = Modifier
				.align(BottomStart)
				.padding(20.dp, 40.dp),
			content = {
				Icon(
					Icons.Default.Home,
					"Return to login screen"
				)
			}
		)
	}
}