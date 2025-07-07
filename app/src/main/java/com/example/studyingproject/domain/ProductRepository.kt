package com.example.studyingproject.domain

import com.example.studyingproject.data.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProduct(id: Int): Product
    suspend fun updateProduct(product: Product): Product
}
