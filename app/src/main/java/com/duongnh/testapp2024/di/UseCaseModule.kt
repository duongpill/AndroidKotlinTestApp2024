package com.duongnh.testapp2024.di

import com.duongnh.testapp2024.domain.usecase.GetUserDetailUseCase
import com.duongnh.testapp2024.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    factory { GetUsersUseCase(get()) }
    factory { GetUserDetailUseCase(get()) }
}