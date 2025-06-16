package com.example.studyingproject.user

import com.example.studyingproject.Logger
import dagger.Lazy
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val logger: Lazy<Logger>): UserRepository {
    override fun auth() {
        logger.get().log("Вы успешно зашли!")
    }
}