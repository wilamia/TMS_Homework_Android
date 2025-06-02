package com.example.studyingproject.presentation

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
    private val useCase: ProductDetailUseCase
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

    fun addToCart(userId: Int, productId: Int) {
        viewModelScope.launch {
            useCase.addToCart(userId, productId)
        }
    }

    fun removeFromCart(cartId: Int) {
        viewModelScope.launch {
            useCase.removeFromCart(cartId)
        }
    }
}