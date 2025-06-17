package com.example.studyingproject.database

import androidx.room.*
import com.example.studyingproject.data.CartProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CartProductDao {
    @Insert
    suspend fun insertProduct(cartProduct: CartProduct)
    @Query("SELECT * FROM CartProduct, CartRequest WHERE cartId = id")
    suspend fun selectProduct(): List<CartProduct>
}