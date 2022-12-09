package com.timer.testtask.data.common

import android.content.SharedPreferences
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TimerHelper(private val sharedPreferences: SharedPreferences) {

    private val scope = MainScope()
    private var job: Job? = null
    var progress = 0
    val progressFlow = MutableStateFlow(0)

    fun start() {
        progress = getSavedProgress()
        job = scope.launch() {
            while (true) {
                progress += 1
                progressFlow.value = progress
                delay(1000)
            }
        }
    }

    fun stop() {
        saveProgress()
        job?.cancel()
        job = null
    }

    private fun saveProgress() {
        sharedPreferences.edit().putInt(TIMER_PROGRESS_KEY, progress).apply()
    }

    private fun getSavedProgress(): Int {
        return sharedPreferences.getInt(TIMER_PROGRESS_KEY, 0)
    }
}