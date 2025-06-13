package com.example.studyingproject

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var progressBar: CustomProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        progressBar = findViewById(R.id.customBar)
        progressBar.setCustomSize(convertPxToDp(200f))
        val animator = ValueAnimator.ofFloat(0f, 100f).apply {
            duration = 10000
            addUpdateListener {
                val progress = it.animatedValue as Float
                progressBar.setProgress(progress)
            }
            start()
        }
    }

    fun convertPxToDp(px: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
    }
}