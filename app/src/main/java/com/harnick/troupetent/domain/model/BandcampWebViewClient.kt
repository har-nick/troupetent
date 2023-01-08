package com.harnick.troupetent.domain.model

import android.content.Context
import android.net.Uri
import android.webkit.*
import com.google.accompanist.web.AccompanistWebViewClient
import com.harnick.troupetent.presentation.screens.login.LoginEvent
import com.harnick.troupetent.presentation.screens.login.LoginViewModel
import java.io.BufferedReader

class BandcampWebViewClient(
	private val loginViewModel: LoginViewModel,
	private val localContext: Context,
) : AccompanistWebViewClient() {
	
	private companion object {
		const val baseUrl = "https://bandcamp.com"
		const val loginUrl = "$baseUrl/login"
		const val loginRequest = "$baseUrl/login_cb"
	}
	
	inner class BandcampWebViewInterface {
		@JavascriptInterface
		fun onLoginSubmit() {
			println("Troupetent: SUBMIT EVENT")
			loginViewModel.onEvent(LoginEvent.ToggleWebViewVisibility(false))
			loginViewModel.onEvent(LoginEvent.LoggingIn)
		}
		
		@JavascriptInterface
		fun onCaptchaServed() {
			println("Troupetent: CAPTCHA SERVED")
			loginViewModel.onEvent(LoginEvent.ToggleWebViewVisibility(true))
		}
	}
	
	lateinit var clientView: WebView
	
	fun goHome() {
		if (clientView.url != loginUrl) {
			loginViewModel.onEvent(LoginEvent.ToggleWebViewVisibility(false))
			clientView.loadUrl(loginUrl)
		}
	}
	
	private val cookieManager: CookieManager = CookieManager.getInstance()
	
	private fun getBandcampCookies() {
		val cookies = cookieManager.getCookie(baseUrl)
		
		loginViewModel.parseBandcampCookies(cookies, localContext)
	}
	
	override fun shouldInterceptRequest(
		view: WebView,
		request: WebResourceRequest?
	): WebResourceResponse? {
		super.shouldInterceptRequest(view, request!!)
		
		if (request.url == Uri.parse(loginRequest)) {
			getBandcampCookies()
		}
		
		return null
	}
	
	private val loginJsPayload = localContext.assets
		.open("www/loginPayload.js")
		.bufferedReader()
		.use(BufferedReader::readText)
	
	override fun onPageFinished(view: WebView?, url: String?) {
		super.onPageFinished(view!!, url!!)
		
		if (url == loginUrl) {
			loginViewModel.onEvent(LoginEvent.ToggleWebViewVisibility(isVisible = true))
			view.evaluateJavascript(loginJsPayload, null)
		}
	}
}