package com.example.trailapp

data class Trail(
    val id: Int,
    val title: String,
    val imageRes: Int, // ID zasobu zdjęcia, np. R.drawable.trail_image
    val distanceKm: Double,
    val elevationGainM: Double,
    val difficulty: String,
    val durationMinutes: Int,
    val routeId: String // Identyfikator używany do nawigacji po kliknięciu (np. "details/1")
)
