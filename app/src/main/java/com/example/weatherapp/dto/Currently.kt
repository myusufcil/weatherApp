package com.example.weatherapp.dto

data class Currently(
    val time: Long,
    val summary: String,
    val icon: String,
    val temperature:String
)