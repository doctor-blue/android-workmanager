package com.devcomentry.android_workmanager.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.devcomentry.android_workmanager.*
import java.lang.Exception

class SummationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Handling A^2 + B^2", applicationContext)
        sleep()
        val numA = inputData.getInt(NUM_A, 0)
        val numB = inputData.getInt(NUM_B, 0)
        return try {
            val outputData = workDataOf(SUMMATION to numA + numB)
            makeStatusNotification("Complete A^2 + B^2 = ${numA + numB}", applicationContext)
            Result.success(outputData)
        } catch (e: Exception) {
            Result.failure()
        }
    }
}