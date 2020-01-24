package com.example.weatherapp.model

data class Content (
    var timezone:String,
    val daily: Daily,
    val hourly: Hourly,
    val currently: Currently
)