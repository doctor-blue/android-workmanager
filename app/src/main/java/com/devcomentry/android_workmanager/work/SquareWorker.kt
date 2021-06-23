package com.devcomentry.android_workmanager.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.devcomentry.android_workmanager.NUM_A
import com.devcomentry.android_workmanager.NUM_B
import com.devcomentry.android_workmanager.makeStatusNotification
import com.devcomentry.android_workmanager.sleep

class SquareWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Square num A and B", applicationContext)
        sleep()
        val numA = inputData.getInt(NUM_A, 0)
        val numB = inputData.getInt(NUM_B, 0)
        return try {
            val outputData = workDataOf(NUM_A to numA * numA, NUM_B to numB * numB)
            makeStatusNotification("Complete square}", applicationContext)
            Result.success(outputData)
        } catch (e: Exception) {
            Result.failure()
        }
    }
}