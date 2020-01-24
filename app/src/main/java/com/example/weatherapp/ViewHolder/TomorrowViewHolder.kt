package com.example.weatherapp.ViewHolder

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.adapter.RecyclerViewClickListener
import com.example.weatherapp.dto.TomorrowDTO
import java.text.SimpleDateFormat
import java.util.*

class TomorrowViewHolder(var view: View) : BaseViewHolder(view) {

    private val item_tomorrow_clock_text        =   view.findViewById(R.id.item_tomorrow_clock_textview) as TextView
    private val item_tomorrow_icon_image_button =   view.findViewById(R.id.item_tomorrow_icon_image_button) as ImageView
    private val item_tomorrow_temp_text         =   view.findViewById(R.id.item_tomorrow_temperature_text) as TextView

    override fun bindView(baseModel: BaseModel, position: Int, clickListener: RecyclerViewClickListener) {

        var item = baseModel as TomorrowDTO

        item_tomorrow_clock_text.text = convertLongToTime(item.time)

        if (item.icon=="partly-cloudy-day"){
            val cloudyDay=R.drawable.ic_iconfinder_weather_icons_cloudy_2087720
            Glide.with(view.context).load(cloudyDay).into(item_tomorrow_icon_image_button)

        }else if(item.icon=="partly-cloudy-night"){
            val cloudyNight=R.drawable.ic_iconfinder_weather_icons_night_cloudy_2087716
            Glide.with(view.context).load(cloudyNight).into(item_tomorrow_icon_image_button)

        }else if (item.icon=="rain"){
            val rain=R.drawable.ic_iconfinder_weather_icons_rainy_2087715
            Glide.with(view.context).load(rain).into(item_tomorrow_icon_image_button)

        }else if (item.icon=="clear-day"){
            val clearDay=R.drawable.ic_iconfinder_weather_icons_sunny_2087721
            Glide.with(view.context).load(clearDay).into(item_tomorrow_icon_image_button)

        } else if (item.icon=="clear-night"){
            val clearNight=R.drawable.ic_iconfinder_weather_icons_night_clear_2087717
            Glide.with(view.context).load(clearNight).into(item_tomorrow_icon_image_button)

        }else if (item.icon=="cloudy"){
            val cloudy=R.drawable.ic_iconfinder_weather_icons_cloudy_2_2087719
            Glide.with(view.context).load(cloudy).into(item_tomorrow_icon_image_button)
        }
        else{
            Log.d("A","Invalid Icon")
        }

        var deneme = item.temperature
        deneme = ((deneme.toDouble() - 32) / 1.8).toInt().toString()+"°"
        item_tomorrow_temp_text.text = deneme

    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("HH mm")
        return format.format(date)
    }
}