package com.example.studyingproject.di

import android.content.Context
import com.example.studyingproject.data.FirebaseService
import com.example.studyingproject.domain.repository.LocationRepository
import com.example.studyingproject.domain.repository.LocationRepositoryImpl
import com.example.studyingproject.domain.repository.MapRepository
import com.example.studyingproject.domain.repository.MapRepositoryImpl
import com.example.studyingproject.domain.usecase.GetAllPOIsUseCase
import com.example.studyingproject.domain.usecase.GetCurrentLocationUseCase
import com.example.studyingproject.domain.usecase.SavePOIUseCase
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideFirebaseService(): FirebaseService = FirebaseService()

    @Provides
    @Singleton
    fun provideLocationRepository(): LocationRepository =
        LocationRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideMapRepository(firebaseService: FirebaseService): MapRepository =
        MapRepositoryImpl(firebaseService)

    @Provides
    fun provideGetCurrentLocationUseCase(repo: LocationRepository) =
        GetCurrentLocationUseCase(repo)

    @Provides
    fun provideGetAllPOIsUseCase(repo: MapRepository) =
        GetAllPOIsUseCase(repo)

    @Provides
    fun provideSavePOIUseCase(repo: MapRepository) =
        SavePOIUseCase(repo)
}
