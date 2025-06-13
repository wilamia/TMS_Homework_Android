package com.example.studyingproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.data.Product
import com.example.studyingproject.domain.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {
    private val _cartItems = MutableLiveData<List<Product>>()
    val cartItems: LiveData<List<Product>> = _cartItems

    fun loadCartItems() {
        viewModelScope.launch {
            _cartItems.value = repository.getCartItems()
        }
    }

    fun removeFromCart(cartId: Int) {
        viewModelScope.launch {
            repository.removeFromCart(cartId)
            val updatedItems = withContext(Dispatchers.IO) { repository.getCartItems() }
            _cartItems.value = updatedItems
        }
    }
}
