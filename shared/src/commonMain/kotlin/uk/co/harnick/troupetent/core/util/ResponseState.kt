package uk.co.harnick.troupetent.core.util

sealed class ResponseState<T> {
    class Success<T>(val data: T? = null) : ResponseState<T>()
    class Saving<T> : ResponseState<T>()
    class Loading<T> : ResponseState<T>()
    class Fetching<T> : ResponseState<T>()
    class Error<T>(val reason: String? = null) : ResponseState<T>()
}
