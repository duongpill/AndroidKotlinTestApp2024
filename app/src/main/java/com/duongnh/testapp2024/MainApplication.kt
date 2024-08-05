package com.duongnh.testapp2024

import android.app.Application
import com.duongnh.testapp2024.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(AppModule)
        }
    }

}