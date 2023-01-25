package com.harnick.troupetent.authentification.domain

import android.content.Context
import android.graphics.Bitmap
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import com.harnick.troupetent.authentification.presentation.screens.login.LoginEvent
import com.harnick.troupetent.authentification.presentation.screens.login.LoginViewModel
import java.io.BufferedReader

class BandcampWebViewClient(
	private val loginViewModel: LoginViewModel,
	private val localContext: Context,
) : AccompanistWebViewClient() {
	
	private companion object {
		const val baseUrl = "https://bandcamp.com"
		const val loginUrl = "$baseUrl/login"
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
	
	private val cookieManager: CookieManager = CookieManager.getInstance()
	
	lateinit var clientView: WebView
	
	fun goHome() {
		if (clientView.url != loginUrl) {
			loginViewModel.onEvent(LoginEvent.WebViewPageLoading)
			clientView.loadUrl(loginUrl)
		}
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
		
		val rawCookies = cookieManager.getCookie(baseUrl)
		
		loginViewModel.onEvent(LoginEvent.WebViewPageLoaded(rawCookies))
	}
}