package com.example.studyingproject.data

import androidx.room.*
import com.example.studyingproject.database.Converters

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = UserDto::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class CartRequest(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val date: String,
    @TypeConverters(Converters::class)  // <-- Add this line
    val products: List<CartProduct>
)