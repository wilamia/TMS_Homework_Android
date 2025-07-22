package com.example.studyingproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.domain.UserLocation
import com.example.studyingproject.domain.usecase.GetAllPOIsUseCase
import com.example.studyingproject.domain.usecase.GetCurrentLocationUseCase
import com.example.studyingproject.domain.usecase.SavePOIUseCase
import com.google.android.gms.maps.model.LatLng
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getAllPOIsUseCase: GetAllPOIsUseCase,
    private val savePOIUseCase: SavePOIUseCase
) : ViewModel() {

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> = _userLocation.asStateFlow()

    private val _poiList = MutableStateFlow<List<UserLocation>>(emptyList())
    val poiList: StateFlow<List<UserLocation>> = _poiList.asStateFlow()

    fun loadUserLocation() {
        viewModelScope.launch {
            val location = getCurrentLocationUseCase()
            _userLocation.value = location
        }
    }

    fun loadAllPOIs() {
        viewModelScope.launch {
            _poiList.value = getAllPOIsUseCase()
        }
    }

    fun savePOI(location: UserLocation) {
        viewModelScope.launch {
            savePOIUseCase(location)
            _poiList.value += location
        }
    }
}
