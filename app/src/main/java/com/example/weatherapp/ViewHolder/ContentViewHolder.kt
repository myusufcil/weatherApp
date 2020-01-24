package com.example.weatherapp.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.dto.ContentDTO
import com.example.weatherapp.adapter.RecyclerViewClickListener
import java.text.SimpleDateFormat
import java.util.*

class ContentViewHolder(var view:View):BaseViewHolder(view) {

    private val item_content_date_textView=view.findViewById(R.id.item_content_date_text) as TextView
    private val item_content_weather_icon_image_button=view.findViewById(R.id.item_content_weather_icon_image_button) as ImageView
    private val item_content_weather_forecast_text=view.findViewById(R.id.item_content_weather_forecast_text) as TextView
    private val item_content_weather_temperature_text=view.findViewById(R.id.recyclerViewContent) as TextView

    override fun bindView(baseModel: BaseModel, position: Int, click: RecyclerViewClickListener) {

        val item = baseModel as ContentDTO

        //Tarih değerini converLongToTime yaparak parse ediyorum ve ekliyorum
        item_content_date_textView.text=convertLongToTime(item.time)

        //forecast kısmını apiden cekiip yazdırıyorum.
        item_content_weather_forecast_text.text=item.summary

        //icon'u urlden alıp direk ekliyoruz.
        val url = "https://darksky.net/images/weather-icons/${item.icon}.png"
        Glide.with(view.context).load(url).into(item_content_weather_icon_image_button)

        //sıcaklıgı fahrenheit alıp Santigrat'a çeviriyoruz.
        var deneme=item.temperature
        deneme= ((deneme.toDouble()-32)/1.8).toInt().toString()+"°"//fahrenheit'tan double şeklinde işlem yapıp işlemden sonra integer'a çevirip sonra string olarak denemenin içine atıp aşağıya yazıyoruz.
        item_content_weather_temperature_text.text=deneme
    }

    fun convertLongToTime(time: Long): String
    {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("EEEE, dd MMM")
        return format.format(date)
    }
}