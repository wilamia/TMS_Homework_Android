package com.example.studyingproject.domain.usecase

import com.example.studyingproject.domain.repository.MapRepository

class GetAllPOIsUseCase(private val repo: MapRepository) {
    suspend operator fun invoke() = repo.getAllLocations()
}