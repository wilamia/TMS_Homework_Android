package com.example.studyingproject.domain

import com.example.studyingproject.data.Product

interface CartRepository {
    suspend fun getCartItems(): List<Product>
    suspend fun removeFromCart(cartId: Int)
}