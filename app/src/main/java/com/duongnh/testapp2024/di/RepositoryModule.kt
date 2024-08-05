package com.duongnh.testapp2024.di

import com.duongnh.testapp2024.data.repository.UserRepositoryImpl
import com.duongnh.testapp2024.domain.repository.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}