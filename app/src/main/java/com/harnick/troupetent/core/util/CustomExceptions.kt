package com.harnick.troupetent.core.util

sealed class CustomExceptions(override var message: String? = null) : Exception() {
	object BandcampLibrarySyncRequired : Exception()
}
