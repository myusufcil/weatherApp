package com.example.weatherapp.dto

data class City (
    var name:String?,
    var lat:String?,
    var lon:String?,
    var isSelect:Boolean= false
):BaseModel {
    override val type: Int
        get() = BaseModel.TYPE_ITEM_LOCATION
}