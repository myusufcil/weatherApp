package com.example.weatherapp.ViewHolder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.model.City
import com.example.weatherapp.adapter.RecyclerViewClickListener

class LocationViewHolder(var view: View):BaseViewHolder(view) {
    private val item_location_textView :TextView =   view.findViewById(R.id.item_location_textview)
    private val item_location_imageView:ImageView = view.findViewById(R.id.item_location_imageView)
    private val rootView:LinearLayout = view.findViewById(R.id.rootView)


    @SuppressLint("ResourceType")

    override fun bindView(baseModel: BaseModel, position: Int, click: RecyclerViewClickListener)
    {
        var item = baseModel as City
        item_location_textView.text=item.name

        rootView.setOnClickListener{
            if (item.isSelect) {
                item.isSelect = false
                item_location_imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        view.context,
                        R.drawable.ic_placeholder
                    )
                )
            }
            //location itemine tıklandıktan sonra iconun değiştirilmesi için contextCompat.getDrawable kullanılıyorum.
            else {
                item.isSelect = true
                item_location_imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        view.context,
                        R.drawable.ic_placeholder_pressed
                    )
                )
            }
            click.onClickListener(position,baseModel)   //location item view için yazılan Interface
        }
    }
}