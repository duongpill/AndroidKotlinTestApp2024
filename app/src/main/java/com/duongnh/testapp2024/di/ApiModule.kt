package com.duongnh.testapp2024.di

import com.duongnh.testapp2024.data.remote.api.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit

val ApiModule = module {
    single { get<Retrofit>().create(UserApi::class.java) }
}