package com.example.studyingproject.data

import com.example.studyingproject.domain.UserLocation
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private val firestore = Firebase.firestore

    suspend fun saveUserLocation(location: UserLocation) {
        val newId = firestore.collection("user_locations").document().id
        firestore.collection("user_locations")
            .document(newId)
            .set(location)
            .await()
    }

    suspend fun getAllUserLocations(): List<UserLocation> {
        val snapshot = firestore.collection("user_locations").get().await()
        return snapshot.documents.mapNotNull { it.toObject(UserLocation::class.java) }
    }
}
