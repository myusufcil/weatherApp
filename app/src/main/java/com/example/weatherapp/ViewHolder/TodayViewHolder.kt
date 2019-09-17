package com.example.weatherapp.ViewHolder

import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.dto.BaseModel
import com.example.weatherapp.dto.RecyclerViewClickListener
import com.example.weatherapp.dto.TodayDTO
import java.text.SimpleDateFormat
import java.util.*

class TodayViewHolder(var view: View) : BaseViewHolder(view) {
    private var item_today_clock_text                   =   view.findViewById(R.id.item_today_time_textView) as TextView
    private var item_today_weather_icon_imageButton     =   view.findViewById(R.id.item_today_icon_image_button) as ImageView
    private var item_today_temperature_text             =   view.findViewById(R.id.item_today_temperature_textView) as TextView



    override fun bindView(baseModel: BaseModel, position: Int,click: RecyclerViewClickListener) {
        var item = baseModel as TodayDTO

        var deneme = item.temperature
        deneme = ((deneme.toDouble() - 32) / 1.8).toInt().toString() + "°"    //fahrenheit'tan double şeklinde işlem yapıp işlemden sonra integer'a çevirip sonra string olarak denemenin içine atıp aşağıya yazıyorum.
        item_today_temperature_text.text = deneme                       //sıcaklığı item_today_temperature_textView ekliyorum

        if (item.icon == "partly-cloudy-day") {
            val cloudyDay = R.drawable.ic_iconfinder_weather_icons_cloudy_2087720
            Glide.with(view.context).load(cloudyDay).into(item_today_weather_icon_imageButton)
        } else if (item.icon == "partly-cloudy-night") {
            val cloudyNight = R.drawable.ic_iconfinder_weather_icons_night_cloudy_2087716
            Glide.with(view.context).load(cloudyNight).into(item_today_weather_icon_imageButton)
        } else if (item.icon == "rain") {
            val rain = R.drawable.ic_iconfinder_weather_icons_rainy_2087715
            Glide.with(view.context).load(rain).into(item_today_weather_icon_imageButton)
        } else if (item.icon == "clear-day") {
            val clearDay = R.drawable.ic_iconfinder_weather_icons_sunny_2087721
            Glide.with(view.context).load(clearDay).into(item_today_weather_icon_imageButton)
        } else if (item.icon == "clear-night") {
            val clearNight = R.drawable.ic_iconfinder_weather_icons_night_clear_2087717
            Glide.with(view.context).load(clearNight).into(item_today_weather_icon_imageButton)
        } else if (item.icon == "cloudy") {
            val cloudy = R.drawable.ic_iconfinder_weather_icons_cloudy_2_2087719
            Glide.with(view.context).load(cloudy).into(item_today_weather_icon_imageButton)
        } else {
            Log.d("A", "Invalid Icon")
        }
        item_today_clock_text.text =
            convertLongToTime(item.time)       //Zamanı item_today_time_textView textviewine elliyoruz.    }
    }
    fun convertLongToTime(time: Long): String
    {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("HH mm")
        return format.format(date)
    }

}