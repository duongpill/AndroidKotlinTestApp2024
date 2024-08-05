package com.duongnh.testapp2024.di

import com.duongnh.testapp2024.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
}