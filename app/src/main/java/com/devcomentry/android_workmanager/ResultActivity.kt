package com.devcomentry.android_workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devcomentry.android_workmanager.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getIntExtra(RESULT, 0)
        binding.txtResult.text = result.toString()

    }
}