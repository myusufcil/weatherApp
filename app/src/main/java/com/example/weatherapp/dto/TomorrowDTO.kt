package com.example.weatherapp.dto

import com.example.weatherapp.model.BaseModel

data class TomorrowDTO (
    val time: Long,
    val icon: String,
    val temperature: String
    ) : BaseModel {
    override val type: Int
    get() = BaseModel.TYPE_ITEM_TOMORROW
}