package com.harnick.troupetent.domain.model

import android.content.Context
import android.graphics.Bitmap
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
			loginViewModel.onEvent(LoginEvent.LoginFormSubmission)
		}
		
		@JavascriptInterface
		fun onCaptchaServed() {
			loginViewModel.onEvent(LoginEvent.CaptchaServed)
		}
	}
	
	lateinit var clientView: WebView
	
	fun goHome() {
		if (clientView.url != loginUrl) {
			loginViewModel.onEvent(LoginEvent.WebViewPageLoading)
			clientView.loadUrl(loginUrl)
		}
	}
	
	private val cookieManager: CookieManager = CookieManager.getInstance()
	
	private fun getBandcampCookies() {
		val cookies = cookieManager.getCookie(baseUrl)
		
		loginViewModel.parseRawBandcampCookies(cookies, localContext)
	}
	
	override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
		super.onPageStarted(view, url, favicon)
		
		loginViewModel.onEvent(LoginEvent.WebViewPageLoading)
	}
	
	private val loginJsPayload = localContext.assets
		.open("www/loginPayload.js")
		.bufferedReader()
		.use(BufferedReader::readText)
	
	override fun onPageFinished(view: WebView?, url: String?) {
		super.onPageFinished(view!!, url!!)
		
		if (url == loginUrl) {
			view.evaluateJavascript(loginJsPayload, null)
		}
		
		loginViewModel.onEvent(LoginEvent.WebViewPageLoaded)
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
}