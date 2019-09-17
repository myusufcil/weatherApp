package com.example.weatherapp.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.dto.BaseModel
import com.example.weatherapp.dto.City
import com.example.weatherapp.dto.RecyclerViewClickListener

class CountryListViewHolder(var view:View): BaseViewHolder (view){

    private var country_add_item_location_text =view.findViewById<TextView>(R.id.country_list_item_location_text)
    private var country_add_item_location_imageView=view.findViewById<ImageView>(R.id.addCountryImageButton)

    override fun bindView(baseModel: BaseModel, position: Int, click: RecyclerViewClickListener) {
        var item = baseModel as City
        country_add_item_location_text.text=item.name

        if (item.isSelect) {
            item.isSelect = false
            country_add_item_location_imageView.setImageDrawable(

                ContextCompat.getDrawable(
                    view.context,
                    R.drawable.ic_placeholder
                )
            )
        }
        //location itemine tıklandıktan sonra iconun değiştirilmesi için contextCompat.getDrawable kullanılıyorum.
        else {
            item.isSelect = true
            country_add_item_location_imageView.setImageDrawable(

                ContextCompat.getDrawable(
                    view.context,
                    R.drawable.ic_placeholder_pressed
                )
            )
        }
        click.onClickListener(position,baseModel)   //location item view için yazılan Interface
    }
}