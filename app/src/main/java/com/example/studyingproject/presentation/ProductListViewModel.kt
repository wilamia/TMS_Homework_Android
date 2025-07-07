package com.example.studyingproject.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.data.Product
import com.example.studyingproject.domain.ProductRepository
import com.example.studyingproject.domain.ProductRepositoryImpl

import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    val products = MutableLiveData<List<Product>>()

    fun fetchProducts() {
        viewModelScope.launch {
            products.value = repository.getProducts()
        }
    }
}