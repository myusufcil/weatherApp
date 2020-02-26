package com.example.weatherapp.dto

import com.example.weatherapp.model.BaseModel

data class IconAndHistoryDTO(val time: Long, val icon: String) :
    BaseModel {
    //Data Transfer Object
    override val type: Int
        get() = BaseModel.TYPE_ITEM_DATE
}
//ads