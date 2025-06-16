package com.example.studyingproject.user

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UserModule {
    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}