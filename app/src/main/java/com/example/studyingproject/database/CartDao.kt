package com.example.studyingproject.database

import androidx.room.*
import com.example.studyingproject.data.CartRequest
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert
    suspend fun insertCart(cartRequest: CartRequest)

    @Query("SELECT * FROM CartRequest")
    fun selectCart(): Flow<List<CartRequest>>
}