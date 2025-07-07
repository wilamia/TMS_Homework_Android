package com.example.studyingproject.domain

import android.content.Context
import android.util.Log
import com.example.studyingproject.data.CartProduct
import com.example.studyingproject.data.CartRequest
import com.example.studyingproject.data.CartResponseDto
import com.example.studyingproject.data.FakeStoreApi
import com.example.studyingproject.data.Product
import com.example.studyingproject.di.FirstApi
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    @FirstApi private val api: FakeStoreApi,
    private val context: Context
) : CartRepository {

    private val localCart = mutableListOf<CartProduct>()

    override suspend fun getCartItems(): List<Product> {
        val allProducts = api.getProducts().map { it.toDomain() }
        val productsMap = allProducts.associateBy { it.id }

        val cartResponse = api.getCartItems(1)
        return cartResponse.toDomain(productsMap)
    }


    override suspend fun updateCart(userId: Int, productId: Int) {
        val existingProduct = localCart.find { it.productId == productId }
        if (existingProduct != null) {
            // Увеличиваем количество
            val updatedProduct = existingProduct.copy(quantity = existingProduct.quantity + 1)
            localCart.remove(existingProduct)
            localCart.add(updatedProduct)
        } else {
            // Добавляем новый товар
            localCart.add(CartProduct(productId, 1))
        }

        // Обновляем корзину на сервере
        val cartRequest = CartRequest(
            userId = userId,
            date = "2025-05-26", // или текущая дата
            products = localCart.toList()
        )
        api.updateCart(
            1,
            cartRequest
        )
    }

    override suspend fun removeFromCart(userId: Int, productId: Int) {
        // Удаляем из локальной корзины
        localCart.removeIf { it.productId == productId }

        // Отправляем обновлённую корзину на сервер
        val cartRequest = CartRequest(
            userId = userId,
            date = "2025-05-26",
            products = localCart.toList()
        )
        api.updateCart(userId, cartRequest)
    }


    fun CartResponseDto.toDomain(productsMap: Map<Int, Product>): List<Product> {
        return this.products.mapNotNull { cartProduct ->
            productsMap[cartProduct.productId]?.copy(quantity = cartProduct.quantity)
        }
    }
}
