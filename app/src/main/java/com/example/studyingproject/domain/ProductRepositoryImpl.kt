package com.example.studyingproject.domain

import com.example.studyingproject.data.CartProduct
import com.example.studyingproject.data.CartRequest
import com.example.studyingproject.data.FakeStoreApi
import com.example.studyingproject.data.Product
import com.example.studyingproject.data.ProductDto

class ProductRepositoryImpl(private val api: FakeStoreApi) : ProductRepository {
    override suspend fun getProducts() = api.getProducts().map { it.toDomain() }
    override suspend fun getProduct(id: Int) = api.getProduct(id).toDomain()
    override suspend fun updateProduct(product: Product): Product {
        val dto =
            ProductDto(product.id, product.title, product.price, product.description, product.image)
        return api.updateProduct(product.id, dto).toDomain()
    }

    override suspend fun addToCart(userId: Int, productId: Int) {
        val cart = CartRequest(userId, "2025-05-26", listOf(CartProduct(productId, 1)))
        api.addToCart(cart)
    }

    override suspend fun removeFromCart(cartId: Int) {
        api.removeFromCart(cartId)
    }
}
