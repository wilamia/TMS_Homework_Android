package com.example.studyingproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studyingproject.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    suspend fun selectProduct(): List<Product>
}