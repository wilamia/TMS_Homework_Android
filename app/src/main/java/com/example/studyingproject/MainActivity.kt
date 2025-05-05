package com.example.studyingproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.studyingproject.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel.state.observe(this, { sum ->
//            binding.textView.text = sum.toString()
//        })
//        binding.firstBtn.setOnClickListener {
////            lifecycleScope.launch (Dispatchers.Main){
////                delay(1500)
////
////                binding.textView.text = Random.nextInt(1..100).toString()
////            }
//            runBlocking {
//                delay(5500)
//                binding.textView.text = Random.nextInt(1..100).toString()
//            }
//        }
//
//        binding.secondBtn.setOnClickListener {
//            binding.textView2.text = binding.textView.text.reversed()
//        }
//
//        val job = lifecycleScope.launch {
//            for (i in 10 downTo 0) {
//                delay(1000)
//                binding.timer.text = i.toString()
//            }
//        }
//
//        binding.cancelBtn.setOnClickListener {
//            if (job.isActive) {
//                job.cancel()
//                binding.timer.text = getString(R.string.timer_was_stopped)
//            } else {
//                Toast.makeText(this, "Timer is end", Toast.LENGTH_SHORT).show()
//            }
//        }


        lifecycleScope.launch {
            val fileResult = async { fileTransfer() }
            val networkResult = async { networkJob() }
            binding.textView.text = fileResult.await()
            binding.textView2.text = networkResult.await()
            binding.timer.text = awaitAll(fileResult, networkResult).toString().replace('[', ' ').replace(']', ' ')
        }
    }

    suspend fun fileTransfer(): String {
        delay(700)
        return "File"
    }

    suspend fun networkJob(): String {
        delay(1500)
        return "Network"
    }
}