package com.example.studyingproject.domain

import com.example.studyingproject.data.CartProduct
import com.example.studyingproject.data.Product

interface CartRepository {
    suspend fun getCartItems(): List<Product>
    suspend fun updateCart(userId: Int, productId: Int)
    suspend fun removeFromCart(userId: Int, productId: Int)
}