package com.example.studyingproject

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel: ViewModel() {
    private val _infoFlow = MutableSharedFlow<String>(replay = 1, extraBufferCapacity = 0)
    val infoFlow: SharedFlow<String> = _infoFlow

    init {
        GlobalScope.launch {
            _infoFlow.emit("1")
        }
    }
    fun clickOnButton() {
        GlobalScope.launch {
            val value = "Button clicked" + Random.nextInt(1, 100).toString()
            Log.d("MYTAG", "$value")
            _infoFlow.emit(value)
        }
    }

    fun getInfoFlow() = infoFlow.filter { it!="Button clicked" + 1}
}