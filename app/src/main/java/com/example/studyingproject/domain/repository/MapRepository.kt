package com.example.studyingproject.domain.repository

import com.example.studyingproject.domain.UserLocation

interface MapRepository {
    suspend fun saveUserLocation(location: UserLocation)
    suspend fun getAllLocations(): List<UserLocation>
}