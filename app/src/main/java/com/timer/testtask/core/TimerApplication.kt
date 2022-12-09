package com.timer.testtask.core

import android.app.Application
import com.timer.testtask.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TimerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TimerApplication)
            modules(appModule)
        }
    }
}