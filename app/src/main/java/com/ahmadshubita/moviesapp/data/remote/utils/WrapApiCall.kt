package com.ahmadshubita.moviesapp.data.remote.utils

import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T> wrapApiCall(request: suspend () -> Response<T>): T {
    return try {
        val result = request()
        when (result.code()) {
            HttpStatusCode.UNAUTHORIZED.code -> throw UnAuthorizedException(result.message())
            HttpStatusCode.NOT_FOUND.code -> throw NullDataException(result.message())
            else -> result.body() ?: throw NullDataException(result.message())
        }
    } catch (e: Exception) {
        throw Exception(e.message)
    }
}

open class MoviesHiveException(message: String?) : Exception(message)
class NullDataException(message: String) : MoviesHiveException(message)
class UnAuthorizedException(message: String?) : MoviesHiveException(message)