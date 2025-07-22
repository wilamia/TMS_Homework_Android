package com.example.studyingproject.di

import com.example.studyingproject.domain.usecase.GetAllPOIsUseCase
import com.example.studyingproject.domain.usecase.GetCurrentLocationUseCase
import com.example.studyingproject.domain.usecase.SavePOIUseCase
import com.example.studyingproject.presentation.MainActivity
import com.example.studyingproject.presentation.MapViewModel
import dagger.Component
import jakarta.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: MapViewModel)
    fun provideGetCurrentLocationUseCase(): GetCurrentLocationUseCase
    fun  provideGetAllPOIsUseCase(): GetAllPOIsUseCase
    fun provideSavePOIUseCase(): SavePOIUseCase
}