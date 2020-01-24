package com.example.weatherapp.model

data class Currently(
    val time: Long,
    val summary: String,
    val icon: String,
    val temperature:String
)