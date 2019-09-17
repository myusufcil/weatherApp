package com.example.weatherapp.dto


interface RecyclerViewClickListener {
    fun onClickListener(position: Int,model: BaseModel)
    fun onLongClickListener(position: Int,model: BaseModel)
}