package com.example.weatherapp.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.dto.IconAndHistoryDTO
import com.example.weatherapp.adapter.RecyclerViewClickListener
import java.text.SimpleDateFormat
import java.util.*


class HistoryViewHolder(var view: View) : BaseViewHolder(view) {
    override fun bindView(baseModel: BaseModel, position: Int, click: RecyclerViewClickListener) {
        val item = baseModel as IconAndHistoryDTO

        val url = "https://darksky.net/images/weather-icons/${item.icon}.png"
        Glide.with(view.context).load(url).into(item_date_icon_text_value)

        item_date_history_text_value.text = convertLongToTime(item.time)
    }

    private val item_date_icon_text_value = view.findViewById(R.id.item_date_spot_image_button) as ImageView
    private val item_date_history_text_value = view.findViewById(R.id.item_date_text_date) as TextView

    private fun convertLongToTime(time: Long): String
    {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("dd MMM")
        return format.format(date)
    }
}