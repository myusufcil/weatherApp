package com.example.weatherapp.dto

import com.google.gson.annotations.SerializedName

data class HourlyData (
    val time:Long,
    @SerializedName("temperature")
    val temperature:String,
    @SerializedName("icon")
    val icon:String
)