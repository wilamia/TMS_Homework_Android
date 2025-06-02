package com.example.studyingproject.domain

import com.example.studyingproject.data.Product

class ProductDetailUseCase(private val repository: ProductRepository) {
    suspend fun getProduct(id: Int): Product = repository.getProduct(id)
    suspend fun updateProduct(product: Product) = repository.updateProduct(product)
    suspend fun addToCart(userId: Int, productId: Int) = repository.addToCart(userId, productId)
    suspend fun removeFromCart(cartId: Int) = repository.removeFromCart(cartId)
}
