package com.example.studyingproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyingproject.domain.usecase.GetAllPOIsUseCase
import com.example.studyingproject.domain.usecase.GetCurrentLocationUseCase
import com.example.studyingproject.domain.usecase.SavePOIUseCase

class MapViewModelFactory(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getAllPOIsUseCase: GetAllPOIsUseCase,
    private val savePOIUseCase: SavePOIUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MapViewModel::class.java) -> {
                MapViewModel(
                    getCurrentLocationUseCase,
                    getAllPOIsUseCase,
                    savePOIUseCase
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
