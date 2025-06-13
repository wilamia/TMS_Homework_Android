package com.example.studyingproject.data

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String
) {
    fun toDomain() = Product(id, title, price, description, image)
}