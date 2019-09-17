package com.example.weatherapp.dto

data class Content (
    var timezone:String,
    val daily: Daily,
    val hourly: Hourly,
    val currently: Currently
)