package com.example.studyingproject.user

import android.content.Context
import com.example.studyingproject.Email
import com.example.studyingproject.Logger
import com.example.studyingproject.Name
import dagger.BindsInstance
import dagger.Lazy
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val logger: Lazy<Logger>,
    private val context: Context,
    @Name private val name: String,
    @Email private val email: String
) : UserRepository {
    override fun auth() {
        logger.get().log("Вы успешно зашли! $name, $email")
    }
}