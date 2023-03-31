package com.harnick.troupetent.authentication.domain

import android.webkit.ConsoleMessage
import com.google.accompanist.web.AccompanistWebChromeClient
import com.harnick.troupetent.authentication.presentation.LoginEvent

class BandcampWebChromeClient(
    private val handleEvent: (LoginEvent) -> Unit
) : AccompanistWebChromeClient() {

    companion object {
        const val delimiter = "Troupetent: "
    }

    private var lastErrorMessage: String? = null

    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        super.onConsoleMessage(consoleMessage)

        val content = consoleMessage.message()

        if (content.contains(delimiter)) {
            val error = content.subSequence(
                startIndex = content.indexOf(delimiter).plus(delimiter.length),
                endIndex = content.indexOf(".").inc()
            ).toString()

//		Console messages seem to repeat a dozen times. Idk.
            if (error != lastErrorMessage) {
                handleEvent(LoginEvent.LoginError(error))
                lastErrorMessage = error
                return true
            }
        }
        return false
    }
}