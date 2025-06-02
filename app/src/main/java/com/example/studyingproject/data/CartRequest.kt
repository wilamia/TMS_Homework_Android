package com.example.studyingproject.data

data class CartRequest(
    val userId: Int,
    val date: String,
    val products: List<CartProduct>
)