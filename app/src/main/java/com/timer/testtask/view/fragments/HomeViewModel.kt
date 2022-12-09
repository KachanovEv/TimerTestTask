package com.timer.testtask.view.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timer.testtask.data.common.TimerHelper
import kotlinx.coroutines.launch

class HomeViewModel(private val timerHelper: TimerHelper) : ViewModel() {

    var progressLiveData = MutableLiveData<Int>()
    var isTimerRunning = false

    fun startTimer() {
        timerHelper.start()
        isTimerRunning = true
        viewModelScope.launch {
            timerHelper.progressFlow.collect{
                progressLiveData.value = it
            }
        }
    }

    fun stopTimer() {
        timerHelper.stop()
        isTimerRunning = false
    }
}