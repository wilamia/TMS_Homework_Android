package com.example.studyingproject

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideLogger(): Logger {
        val tag =
        if (BuildConfig.DEBUG) "debug"
            else "production"
        val logger = Logger(tag)
        return logger
    }
}