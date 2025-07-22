package com.example.studyingproject.domain.repository

import com.example.studyingproject.data.FirebaseService
import com.example.studyingproject.domain.UserLocation

class MapRepositoryImpl(private val firebase: FirebaseService) : MapRepository {
    override suspend fun saveUserLocation(location: UserLocation) = firebase.saveUserLocation(location)
    override suspend fun getAllLocations(): List<UserLocation> = firebase.getAllUserLocations()
}