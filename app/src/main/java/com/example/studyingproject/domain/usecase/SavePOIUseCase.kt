package com.example.studyingproject.domain.usecase

import com.example.studyingproject.domain.UserLocation
import com.example.studyingproject.domain.repository.MapRepository

class SavePOIUseCase(private val repo: MapRepository) {
    suspend operator fun invoke(location: UserLocation) = repo.saveUserLocation(location)
}