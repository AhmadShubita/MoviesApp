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
        throw NewsHiveException(e.message)
    } catch (e: IOException) {
        throw RateLimitExceededException(e.message)
    } catch (e: UnknownHostException) {
        throw NoInternetException(e.message)
    }
}

open class NewsHiveException(message: String?) : Exception(message)
class NullDataException(message: String) : NewsHiveException(message)
open class NetworkException(message: String?) : NewsHiveException(message)
class RateLimitExceededException(message: String?) : NetworkException(message)
class NoInternetException(message: String?) : NetworkException(message)
class UnAuthorizedException(message: String?) : NewsHiveException(message)