import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.remember
import com.example.studyingproject.domain.UserLocation
import com.example.studyingproject.presentation.MapViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun MapScreen(
    viewModel: MapViewModel,
    modifier: Modifier = Modifier
) {
    val userLocationState = viewModel.userLocation.collectAsState()
    val poiListState = viewModel.poiList.collectAsState()

    val userLocation = userLocationState.value
    val poiList = poiListState.value

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadUserLocation()
        viewModel.loadAllPOIs()
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation ?: LatLng(0.0, 0.0), 14f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true),
        uiSettings = MapUiSettings(zoomControlsEnabled = true),
        onMapLongClick = { latLng ->

            val newLocation = UserLocation(
                userId = "someUserId",
                latitude = latLng.latitude,
                longitude = latLng.longitude
            )
            viewModel.savePOI(newLocation)

            coroutineScope.launch {
                snackbarHostState.showSnackbar("Точка интереса добавлена")
            }
        }
    ) {
        userLocation?.let {
            Marker(state = rememberMarkerState(position = it), title = "You")
        }

        poiList.forEach {
            val randomHue = Random.nextFloat() * 360f
            Marker(
                state = rememberMarkerState(position = LatLng(it.latitude, it.longitude)),
                title = "POI",
                icon = BitmapDescriptorFactory.defaultMarker(randomHue)
            )
        }
    }

    SnackbarHost(hostState = snackbarHostState)
}
