package com.example.studyingproject.presentation

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.data.Product
import com.example.studyingproject.domain.ProductRepository
import com.example.studyingproject.domain.ProductRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.util.UUID

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val products = MutableLiveData<List<Product>>()
    init {
        sharedPreferences.edit().putInt("user_id", 1).apply()
    }
    fun fetchProducts() {
        viewModelScope.launch {
            products.value = repository.getProducts()
        }
    }
}