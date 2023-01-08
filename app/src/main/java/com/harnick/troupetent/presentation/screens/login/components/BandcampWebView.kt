package com.harnick.troupetent.presentation.screens.login.components

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
import com.harnick.troupetent.domain.model.BandcampWebChromeClient
import com.harnick.troupetent.domain.model.BandcampWebViewClient
import com.harnick.troupetent.presentation.screens.login.LoginViewModel

@Composable
fun BandcampWebView(
	loginUrl: String,
	viewModel: LoginViewModel,
	webViewIsVisible: Boolean
) {
	val context = LocalContext.current
	
	val webViewAlpha: Float by animateFloatAsState(
		if (webViewIsVisible) 1f else 0f,
//	OnPageFinished != DOM painted. Delay just helps a bit.
		tween(300, 100)
	)
	val webViewState = rememberWebViewState(loginUrl)
	val webViewNavigator = rememberWebViewNavigator()
	val webChromeClient = BandcampWebChromeClient(viewModel)
//	Remember prevents webViewClient.clientView property from being overwritten.
	val webViewClient = remember {
		BandcampWebViewClient(viewModel, context)
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