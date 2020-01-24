package com.example.weatherapp.model

import com.example.weatherapp.model.HourlyData
import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("data")
    val hourlyData: List<HourlyData>
)