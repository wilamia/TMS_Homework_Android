package com.example.studyingproject.presentation

import android.content.SharedPreferences
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
    private val repository: CartRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _cartItems = MutableLiveData<List<Product>>()
    val cartItems: LiveData<List<Product>> = _cartItems
    val userId = sharedPreferences.getInt("user_id", 1)

    fun loadCartItems() {
        viewModelScope.launch {
            if (userId != -1) {
                _cartItems.value = repository.getCartItems(userId)
            } else {
                _cartItems.value = emptyList()
            }
        }
    }

    fun removeFromCart(cartId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(cartId)
            val updatedItems = repository.getCartItems(userId)
            withContext(Dispatchers.Main) {
                _cartItems.value = updatedItems
            }
        }

    }
}
