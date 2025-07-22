package com.example.studyingproject.domain.usecase

import com.example.studyingproject.domain.repository.LocationRepository

class GetCurrentLocationUseCase(private val repo: LocationRepository) {
    suspend operator fun invoke() = repo.getCurrentLocation()
}