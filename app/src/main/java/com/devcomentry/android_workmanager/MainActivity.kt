package com.devcomentry.android_workmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devcomentry.android_workmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            CalcViewModel.CalcViewModelFactory(application)
        )[CalcViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnCancel.setOnClickListener {

            }

            btnHandling.setOnClickListener {
                makeStatusNotification("Nothing can come to happen!", this@MainActivity)

            }

            btnSeeResult.setOnClickListener {
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(RESULT, 25)
                startActivity(intent)

            }
        }
    }

    private fun showWorkInProgress() {
        with(binding) {
            btnHandling.visibility = View.GONE
            btnCancel.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
            btnSeeResult.visibility = View.GONE
        }
    }

    private fun showWorkFinished() {
        with(binding) {
            btnHandling.visibility = View.VISIBLE
            btnCancel.visibility = View.GONE
            progressBar.visibility = View.GONE
            btnSeeResult.visibility = View.VISIBLE
        }
    }

}