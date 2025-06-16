package com.example.studyingproject

import android.util.Log
import dagger.Provides

class Logger(private val tag: String) {
    fun log(message: String) {
        Log.d(tag, message)
    }
}