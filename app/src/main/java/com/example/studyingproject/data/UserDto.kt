package com.example.studyingproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDto(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val username: String,
    val email: String,
    val password: String
)