package com.example.studyingproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val items = List(100) {
            it
        }

        val a = items.asFlow().filter {
            it % 2 != 0
        }.map {
            it * 1.5
        }

        val flow2 = flowOf(3, 5, 4)
        val flow1 = flowOf("Tom", "Jerry", "Spike")
            .zip(flow2) { name, age ->
            Cat(name, age)
        }


        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            viewModel.clickOnButton()
        }

        lifecycleScope.launch {


            viewModel.getInfoFlow().collect {
                delay(1000)
                textView.text = it
            }

//            flow1.collect{
//                Log.d("MYTAG", it.toString())
//            }

//            a.collect {
//                Log.d("MYTAG", "it - $it")
//            }
        }

    }

}
data class Cat(
    val name: String,
    val age: Int
)