package com.example.weatherapp.adapter

import com.example.weatherapp.model.BaseModel


interface RecyclerViewClickListener {
    fun onClickListener(position: Int,model: BaseModel)
    fun onLongClickListener(position: Int,model: BaseModel)
}