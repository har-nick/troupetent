package com.harnick.troupetent.domain.model

sealed class BandcampExceptions(override var message: String? = null) : Exception() {
	object SyncRequiredException : Exception()
}