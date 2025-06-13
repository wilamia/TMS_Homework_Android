package com.example.studyingproject.data

data class CartResponseDto(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<CartProductDto>
)