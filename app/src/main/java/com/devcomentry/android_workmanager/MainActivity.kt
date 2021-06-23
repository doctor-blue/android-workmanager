package com.devcomentry.android_workmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devcomentry.android_workmanager.databinding.ActivityMainBinding
import kotlin.math.abs

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
                viewModel.cancelWork()
            }

            btnHandling.setOnClickListener {
                val numA = abs(edtNumA.text.toString().toInt())
                val numB = abs(edtNumB.text.toString().toInt())
                viewModel.add(numA, numB)

            }

            btnSeeResult.setOnClickListener {
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(RESULT, viewModel.result)
                startActivity(intent)
            }
        }

        viewModel.outputWorkInfos.observe(this, { listOfWorkInfo ->
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@observe
            }
            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()

                val result = workInfo.outputData.getInt(SUMMATION, -1)

                if (result != -1) {
                    binding.btnSeeResult.visibility = View.VISIBLE
                    viewModel.result = result
                }

            } else {
                showWorkInProgress()
            }
        })
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
        }
    }

}