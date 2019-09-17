package com.example.weatherapp.dto

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("data")
    val hourlyData: List<HourlyData>
)