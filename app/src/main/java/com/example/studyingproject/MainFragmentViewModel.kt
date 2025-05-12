package com.example.studyingproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainFragmentViewModel: ViewModel() {
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _list = MutableStateFlow<List<String>>(listOf("apple", "banana", "cherry", "date", "grape", "melon"))
    val list: StateFlow<List<String>> = _list


    val queryFlow = MutableStateFlow("")

    init {
        queryFlow
            .debounce(300)
            .distinctUntilChanged()
            .onEach { query -> search(query) }
            .launchIn(viewModelScope)
    }

    fun search(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) {
                _searchResults.value = emptyList()
                return@launch
            }

            _isLoading.value = true
            _errorMessage.value = null

            try {
                delay(1000)
                randomException()
                val result = simulateSearch(query)
                if (result.isEmpty()) {
                    _searchResults.value = emptyList()
                } else {
                    _searchResults.value = result
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _searchResults.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun simulateSearch(query: String): List<String> {
        return _list.value.filter { it.contains(query, ignoreCase = true) }
    }

    private suspend fun randomException() {
        delay(100)
        if (Random.nextBoolean()) throw Exception("Ошибка при загрузке")
    }
}
