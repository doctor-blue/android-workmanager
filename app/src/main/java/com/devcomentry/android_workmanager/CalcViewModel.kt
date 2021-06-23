package com.devcomentry.android_workmanager

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CalcViewModel(application: Application) : ViewModel() {
    internal var result: Int = 0

    class CalcViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CalcViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CalcViewModel(application) as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}