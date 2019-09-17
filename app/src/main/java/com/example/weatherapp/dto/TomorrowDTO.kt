package com.example.weatherapp.dto

data class TomorrowDTO (
    val time: Long,
    val icon: String,
    val temperature: String
    ) : BaseModel {
    override val type: Int
    get() = BaseModel.TYPE_ITEM_TOMORROW
}