package com.example.weatherapp.dto

import com.example.weatherapp.model.BaseModel

data class CityDTO (
    var name:String?,
    var lat:String?,
    var lon:String?,
    var isSelect:Boolean= false
): BaseModel {
    override val type: Int
        get() = BaseModel.TYPE_ITEM_LOCATION
}