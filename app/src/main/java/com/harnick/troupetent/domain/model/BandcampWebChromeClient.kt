package com.harnick.troupetent.domain.model

import android.webkit.ConsoleMessage
import com.google.accompanist.web.AccompanistWebChromeClient
import com.harnick.troupetent.presentation.screens.login.LoginEvent
import com.harnick.troupetent.presentation.screens.login.LoginViewModel

class BandcampWebChromeClient(
	private val viewModel: LoginViewModel
) : AccompanistWebChromeClient() {
	
	companion object {
		const val delimiter = "Troupetent: "
	}
	
	private var lastErrorMessage: String? = null
	
	override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
		super.onConsoleMessage(consoleMessage)
		
		val content = consoleMessage.message()
		
		if (content.contains(delimiter)) {
			val error = content.substringAfter(delimiter)
			
			if (error != lastErrorMessage) {
				viewModel.onEvent(LoginEvent.LoginError(error))
				lastErrorMessage = error
				return true
			}
		}
		return false
	}
}