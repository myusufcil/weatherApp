package com.example.weatherapp.dto

data class Daily(
    val summary: String,
    val data: List<Data>
)