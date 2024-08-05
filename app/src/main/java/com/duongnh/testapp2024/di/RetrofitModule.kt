package com.duongnh.testapp2024.di

import com.duongnh.testapp2024.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

val RetrofitModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single { retrofitBuilder() }
}

private fun Scope.retrofitBuilder() = Retrofit.Builder().apply {
    baseUrl(Constants.BASE_URL)
    addConverterFactory(GsonConverterFactory.create(get()))
    client(get())
}.build()

private fun Scope.retrofitHttpClient() = OkHttpClient.Builder().apply {
    cache(get())
    connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    retryOnConnectionFailure(true)
    addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
}.build()
