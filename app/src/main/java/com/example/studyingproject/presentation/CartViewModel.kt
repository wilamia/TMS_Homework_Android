package com.example.studyingproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.data.CartProduct
import com.example.studyingproject.data.Product
import com.example.studyingproject.domain.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {
    private val _cartItems = MutableLiveData<List<Product>>()
    val cartItems: LiveData<List<Product>> = _cartItems

    fun loadCartItems() {
        viewModelScope.launch {
            val items = repository.getCartItems()
            _cartItems.postValue(items)
        }
    }

    fun removeFromCart(userId: Int, productId: Int) {
        viewModelScope.launch {
            repository.removeFromCart(userId, productId)
            loadCartItems()  // обновляем список после удаления
        }
    }

}
