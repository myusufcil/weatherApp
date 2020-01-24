package com.example.weatherapp.dto

import com.example.weatherapp.model.BaseModel

data class TodayDTO(
    val time: Long,
    val icon: String,
    val temperature: String
) : BaseModel {
    override val type: Int
        get() = BaseModel.TYPE_ITEM_TODAY
}
