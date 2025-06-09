package com.example.studyingproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studyingproject.data.UserDto
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userDto: UserDto)

    @Query("SELECT * FROM UserDto")
    fun getUsers(): Flow<List<UserDto>>
}