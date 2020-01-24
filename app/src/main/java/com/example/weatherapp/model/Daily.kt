package com.example.weatherapp.model

import com.example.weatherapp.model.Data

data class Daily(
    val summary: String,
    val data: List<Data>
)