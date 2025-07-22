package com.example.studyingproject.domain.repository

import com.google.android.gms.maps.model.LatLng

interface LocationRepository {
    suspend fun getCurrentLocation(): LatLng?
}