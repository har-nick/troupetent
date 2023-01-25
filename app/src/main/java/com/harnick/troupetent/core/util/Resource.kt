package com.harnick.troupetent.core.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
	class Success<T>(data: T? = null) : Resource<T>(data)
	class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
	class Loading<T>(message: String? = null) : Resource<T>(null, message)
	class Fetching<T>(message: String? = null) : Resource<T>(null, message)
	class Saving<T>(message: String? = null) : Resource<T>(null, message)
}