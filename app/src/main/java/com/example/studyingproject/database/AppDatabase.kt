package com.example.studyingproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studyingproject.data.CartProduct
import com.example.studyingproject.data.CartRequest
import com.example.studyingproject.data.Product
import com.example.studyingproject.data.UserDto

@Database(entities = [Product::class, CartRequest::class, CartProduct::class, UserDto::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun CartDao(): CartDao
    abstract fun CartProductDao(): CartProductDao
    abstract fun UserDao(): UserDao
    abstract fun ProductDao(): ProductDao
}