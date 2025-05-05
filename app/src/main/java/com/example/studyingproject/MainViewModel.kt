package com.example.studyingproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    private val list: MutableList<Int> = mutableListOf()
    private var _state: MutableLiveData<Int> = MutableLiveData(0)
    public var state: LiveData<Int> = _state

    init {
        viewModelScope.launch (Dispatchers.Default) {
            repeat(100000) { i ->
                list.add(i)
            }

            var sum = 0

            withContext(Dispatchers.IO) {
                delay(1000)
                sum = list.sum()
                Log.i("MYTAG", sum.toString())
            }

            withContext(Dispatchers.Main) {
                _state.postValue(sum)
                Log.i("MYTAG", sum.toString())
            }
        }

    }
}