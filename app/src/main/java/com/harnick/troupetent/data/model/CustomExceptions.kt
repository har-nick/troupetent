package com.harnick.troupetent.data.model

sealed class BandcampExceptions(override var message: String? = null) : Exception() {
	object SyncRequiredException : Exception()
}