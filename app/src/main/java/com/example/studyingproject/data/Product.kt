package com.example.studyingproject.data

import androidx.room.*

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val quantity: Int = 1
)