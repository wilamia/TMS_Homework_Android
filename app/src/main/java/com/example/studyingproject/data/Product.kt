package com.example.studyingproject.data

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val quantity: Int = 1
)