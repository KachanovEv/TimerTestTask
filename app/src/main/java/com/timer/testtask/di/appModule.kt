package com.timer.testtask.di

import android.app.Application
import android.content.SharedPreferences
import com.timer.testtask.data.common.TimerHelper
import com.timer.testtask.data.common.SHARED_PREFERENCES_NAME
import com.timer.testtask.view.fragments.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { TimerHelper(get()) }
    single{ getSharedPrefs(androidApplication()) }

    single<SharedPreferences.Editor> { getSharedPrefs(androidApplication()).edit() }
    viewModel{ HomeViewModel(get()) }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return  androidApplication.getSharedPreferences(SHARED_PREFERENCES_NAME,  android.content.Context.MODE_PRIVATE)
}