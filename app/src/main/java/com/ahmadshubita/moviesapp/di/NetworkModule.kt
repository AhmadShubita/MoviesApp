package com.ahmadshubita.moviesapp.di

import com.ahmadshubita.moviesapp.BuildConfig
import com.ahmadshubita.moviesapp.data.remote.core.MainInterceptor
import com.ahmadshubita.moviesapp.data.remote.core.MainServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpsLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor, mainInterceptor: MainInterceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
        .addInterceptor(mainInterceptor).build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gsonConverterFactory).build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MainServices =
        retrofit.create(MainServices::class.java)

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}