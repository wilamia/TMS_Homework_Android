package com.example.studyingproject.presentation

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.data.Product
import com.example.studyingproject.domain.ProductDetailUseCase
import com.example.studyingproject.domain.ProductRepository
import com.example.studyingproject.domain.ProductRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val useCase: ProductDetailUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val product = MutableLiveData<Product>()

    fun loadProduct(id: Int) {
        viewModelScope.launch {
            product.value = useCase.getProduct(id)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            useCase.updateProduct(product)
        }
    }

    fun addToCart(productId: Int, cartId: Int) {
        val userId = sharedPreferences.getInt("user_id", -1)
        if (userId != -1) {
            viewModelScope.launch {
                useCase.addToCart(userId, productId, cartId)
            }
        }
    }

    fun removeFromCart(cartId: Int) {
        viewModelScope.launch {
            useCase.removeFromCart(cartId)
        }
    }
}