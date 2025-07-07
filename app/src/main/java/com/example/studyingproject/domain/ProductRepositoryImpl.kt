package com.example.studyingproject.domain

import com.example.studyingproject.data.CartProduct
import com.example.studyingproject.data.CartRequest
import com.example.studyingproject.data.FakeStoreApi
import com.example.studyingproject.data.Product
import com.example.studyingproject.data.ProductDto
import com.example.studyingproject.di.SecondApi
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    @SecondApi private val api: FakeStoreApi) :
    ProductRepository {
    override suspend fun getProducts() = api.getProducts().map { it.toDomain() }
    override suspend fun getProduct(id: Int) = api.getProduct(id).toDomain()
    override suspend fun updateProduct(product: Product): Product {
        val dto =
            ProductDto(product.id, product.title, product.price, product.description, product.image)
        return api.updateProduct(product.id, dto).toDomain()
    }

}
