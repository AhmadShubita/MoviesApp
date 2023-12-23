package com.ahmadshubita.moviesapp.data.remote.core

import com.ahmadshubita.moviesapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MainInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithAccessKey =
            chain.request().url.newBuilder().addQueryParameter(API_KEY_NAME, BuildConfig.API_KEY)
                .build()
        val requestWithAccessKey = chain.request().newBuilder().url(urlWithAccessKey).build()
        return chain.proceed(requestWithAccessKey)
    }

    companion object {
        const val API_KEY_NAME = "api_key"
    }
}