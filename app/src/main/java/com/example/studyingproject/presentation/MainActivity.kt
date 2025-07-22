package com.example.studyingproject.presentation

import MapScreen
import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.studyingproject.di.AppModule
import com.example.studyingproject.di.DaggerAppComponent
import com.example.studyingproject.ui.theme.StudyingProjectTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()

        val factory = MapViewModelFactory(
            appComponent.provideGetCurrentLocationUseCase(),
            appComponent.provideGetAllPOIsUseCase(),
            appComponent.provideSavePOIUseCase()
        )

        viewModel = ViewModelProvider(this, factory)[MapViewModel::class.java]

        setContent {
            StudyingProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MapScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

    }
    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}
