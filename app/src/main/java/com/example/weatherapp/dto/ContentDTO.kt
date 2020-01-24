package com.example.weatherapp.dto

import com.example.weatherapp.model.BaseModel

data class ContentDTO(
    val time: Long,
    val summary: String,
    val icon: String,
    val temperature:String
) : BaseModel {
    override val type: Int
        get() = BaseModel.TYPE_ITEM_CONTENT
}