package com.example.weatherapp.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.dto.BaseModel
import com.example.weatherapp.dto.RecyclerViewClickListener

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindView(baseModel: BaseModel, position: Int,click: RecyclerViewClickListener)
}