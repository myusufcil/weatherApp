package com.example.weatherapp.ViewHolder

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherapp.R
import com.example.weatherapp.dto.BaseModel
import com.example.weatherapp.dto.IconAndHistoryDTO
import com.example.weatherapp.dto.RecyclerViewClickListener
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


class HistoryViewHolder(var view: View) : BaseViewHolder(view) {
    override fun bindView(baseModel: BaseModel, position: Int,click: RecyclerViewClickListener) {
        val item = baseModel as IconAndHistoryDTO

        val url = "https://darksky.net/images/weather-icons/${item.icon}.png"
        Glide.with(view.context).load(url).into(item_date_icon_text_value)

        item_date_history_text_value.text = convertLongToTime(item.time)
    }

    private val item_date_icon_text_value = view.findViewById(R.id.item_date_spot_image_button) as ImageView
    private val item_date_history_text_value = view.findViewById(R.id.item_date_text_date) as TextView



    fun convertLongToTime(time: Long): String
    {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("dd MMM")
        return format.format(date)
    }

//    private fun  convertISOTimeToDate(isoTime:String):String{
//        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//        var convertedDate: Date? = null
//        var formattedDate = ""
//        try {
//            convertedDate = sdf.parse(isoTime)
//            formattedDate = SimpleDateFormat("EEE, d MMM yyyy , HH:mm:ss").format(convertedDate)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//
//        return formattedDate
//    }
}