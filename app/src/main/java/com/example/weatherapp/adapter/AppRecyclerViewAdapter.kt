package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.ViewHolder.*
import com.example.weatherapp.model.BaseModel

class AppRecyclerViewAdapter(
    var items: MutableList<BaseModel>,
    var recyclerViewItemClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        var layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            BaseModel.TYPE_ITEM_DATE ->
                HistoryViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_date,
                        parent,
                        false
                    )
                )
            BaseModel.TYPE_ITEM_TODAY ->
                TodayViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_today,
                        parent,
                        false
                    )
                )
            BaseModel.TYPE_ITEM_TOMORROW ->
                TomorrowViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_tomorrow,
                        parent,
                        false
                    )
                )
            BaseModel.TYPE_ITEM_CONTENT ->
                ContentViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_content,
                        parent,
                        false
                    )
                )
            BaseModel.TYPE_ITEM_LOCATION ->
                LocationViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_location,
                        parent,
                        false
                    )
                )
            BaseModel.TYPE_COUNTRY_LIST_LOCATION->
                CountryListViewHolder(
                    layoutInflater.inflate(
                        R.layout.country_list_item_location,
                        parent,
                        false
                    )
                )
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (items[position].type) {
            BaseModel.TYPE_ITEM_DATE -> {
                (holder as HistoryViewHolder).bindView(items[position], position,recyclerViewItemClickListener)
            }
            BaseModel.TYPE_ITEM_TODAY -> {
                (holder as TodayViewHolder).bindView(items[position], position,recyclerViewItemClickListener)
            }
            BaseModel.TYPE_ITEM_TOMORROW -> {
                (holder as TomorrowViewHolder).bindView(items[position], position,recyclerViewItemClickListener)
            }
            BaseModel.TYPE_ITEM_CONTENT -> {
                (holder as ContentViewHolder).bindView(items[position], position,recyclerViewItemClickListener)
            }
            BaseModel.TYPE_ITEM_LOCATION -> {
                (holder as LocationViewHolder).bindView(items[position], position,recyclerViewItemClickListener!!)
            }
            BaseModel.TYPE_COUNTRY_LIST_LOCATION->{
                (holder as CountryListViewHolder).bindView(items[position], position,recyclerViewItemClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int { //  !!!Burayı sor!!!
        return items[position].type
    }
}