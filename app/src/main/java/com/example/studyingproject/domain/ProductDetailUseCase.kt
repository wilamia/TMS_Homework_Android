package com.example.studyingproject.domain

import com.example.studyingproject.data.Product
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun getProduct(id: Int): Product = repository.getProduct(id)
    suspend fun updateProduct(product: Product) = repository.updateProduct(product)
}
