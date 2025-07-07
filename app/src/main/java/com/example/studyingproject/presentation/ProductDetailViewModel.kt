package com.example.studyingproject.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.data.CartProduct
import com.example.studyingproject.data.Product
import com.example.studyingproject.domain.CartRepository
import com.example.studyingproject.domain.CartRepositoryImpl
import com.example.studyingproject.domain.ProductDetailUseCase
import com.example.studyingproject.domain.ProductRepository
import com.example.studyingproject.domain.ProductRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val useCase: ProductDetailUseCase,
    private val cartRepository: CartRepository
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

    fun addToCart(userId: Int, productId: Int, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                cartRepository.updateCart(userId, productId)
                onComplete(true)
            } catch (e: Exception) {
                onComplete(false)
            }
        }
    }



}