package com.example.weatherapp.model

interface BaseModel {

    val type:Int

    companion object
    {
        const val TYPE_ITEM_DATE=1
        const val TYPE_ITEM_TODAY=2
        const val TYPE_ITEM_TOMORROW=3
        const val TYPE_ITEM_CONTENT=4
        const val TYPE_ITEM_LOCATION=5
        const val TYPE_COUNTRY_LIST_LOCATION=6
    }
}