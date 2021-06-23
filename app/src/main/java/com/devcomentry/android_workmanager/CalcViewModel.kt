package com.devcomentry.android_workmanager

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.devcomentry.android_workmanager.work.SquareWorker
import com.devcomentry.android_workmanager.work.SummationWorker

class CalcViewModel(application: Application) : ViewModel() {
    internal val outputWorkInfos: LiveData<List<WorkInfo>>
    private val workManager = WorkManager.getInstance(application)
    internal var result: Int = 0

    init {
        outputWorkInfos = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
    }

    internal fun add(numA: Int, numB: Int) {
        val dataBuilder = Data.Builder().putInt(NUM_A, numA)
            .putInt(NUM_B, numB).build()

        val numARequest = OneTimeWorkRequestBuilder<SquareWorker>()
            .setInputData(dataBuilder).build()

//        workManager.enqueue(numARequest)
//        var continuation = workManager.beginWith(numARequest)
        var continuation = workManager.beginUniqueWork(
            SUMMATION_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            numARequest
        )

        val summation = OneTimeWorkRequestBuilder<SummationWorker>().addTag(TAG_OUTPUT).build()
        continuation = continuation.then(summation)

        continuation.enqueue()
    }

    internal fun cancelWork() {
        workManager.cancelUniqueWork(SUMMATION_WORK_NAME)
    }
2

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