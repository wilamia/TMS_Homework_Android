package com.example.studyingproject.database

import androidx.room.TypeConverter
import com.example.studyingproject.data.CartProduct
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCartProductList(products: List<CartProduct>): String {
        return gson.toJson(products)
    }

    @TypeConverter
    fun toCartProductList(productsJson: String): List<CartProduct> {
        val listType = object : TypeToken<List<CartProduct>>() {}.type
        return gson.fromJson(productsJson, listType)
    }
}