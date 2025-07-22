package com.example.studyingproject

import com.example.studyingproject.data.FirebaseService
import com.example.studyingproject.domain.UserLocation
import com.example.studyingproject.domain.repository.MapRepository
import com.example.studyingproject.domain.repository.MapRepositoryImpl
import com.google.android.gms.maps.model.LatLng
import org.junit.Before
import org.junit.Test
import org.mockito.invocation.Location
import org.mockito.kotlin.mock
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MapRepositoryTest {
    private val testLocation = UserLocation("user123", 51.0, 12.0)
    private val poiList = listOf(
        UserLocation("poi1", 10.0, 10.0),
        UserLocation("poi2", 20.0, 20.0)
    )
    private val firebaseService = mock<FirebaseService>()
    private lateinit var mapRepository: MapRepositoryImpl

    @Before
    fun init() {
        mapRepository = MapRepositoryImpl(firebaseService)
    }

    @Test
    fun saveUserLocation_enable() = runTest {
        mapRepository.saveUserLocation(testLocation)

        verify(firebaseService).saveUserLocation(testLocation)
    }

    @Test
    fun getAllUserLocations_notNull() = runTest {
        whenever(firebaseService.getAllUserLocations()).thenReturn(poiList)

        val result = mapRepository.getAllLocations()

        assert(result == poiList)
        verify(firebaseService).getAllUserLocations()
    }

    @Test
    fun getAllUserLocations_null() = runTest {
        whenever(firebaseService.getAllUserLocations()).thenReturn(emptyList())

        val result = mapRepository.getAllLocations()

        assert(result.isEmpty())
        verify(firebaseService).getAllUserLocations()
    }
}