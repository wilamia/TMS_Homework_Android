package com.example.studyingproject.domain

import com.example.studyingproject.data.CartResponseDto
import com.example.studyingproject.data.FakeStoreApi
import com.example.studyingproject.data.Product
import okhttp3.OkHttpClient
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val api: FakeStoreApi) : CartRepository {

    override suspend fun getCartItems(userId: Int): List<Product> {
        val cartResponse = api.getCartItems(userId)

        val allProducts = api.getProducts().map { it.toDomain() }
        val productsMap = allProducts.associateBy { it.id }

        return cartResponse.toDomain(productsMap)
    }

    override suspend fun removeFromCart(cartId: Int) {
        api.removeFromCart(cartId)
    }

    fun CartResponseDto.toDomain(productsMap: Map<Int, Product>): List<Product> {
        return this.products.mapNotNull { cartProduct ->
            productsMap[cartProduct.productId]?.copy(quantity = cartProduct.quantity)
        }
    }

}
