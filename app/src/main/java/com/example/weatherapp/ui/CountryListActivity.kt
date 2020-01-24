package com.example.weatherapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.AppRecyclerViewAdapter
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.model.City
import com.example.weatherapp.adapter.RecyclerViewClickListener
import kotlinx.android.synthetic.main.country_list_activity.*
import org.json.JSONArray
import java.io.InputStream

class CountryListActivity : AppCompatActivity() {

    lateinit var recyclerViewCountryAdapter: AppRecyclerViewAdapter
    lateinit var recyclerViewCountryListRecyclerView: RecyclerView

    val cityList: ArrayList<BaseModel> = arrayListOf()
    private val countryAddList = mutableListOf<BaseModel>()

    private var recyclerViewItemClickListener = object :
        RecyclerViewClickListener {

        override fun onClickListener(position: Int, model: BaseModel) {

            if (model is City) {
                //Click Listener
                var intent=Intent()
                intent.putExtra("id",model.lat + "," + model.lon)
                setResult(1001,intent)
                this@CountryListActivity.finish()
            }
        }
        override fun onLongClickListener(position: Int, model: BaseModel) {
            //Long Click Listener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.country_list_activity)
        //toolbar BackPressed
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionbar = supportActionBar
        actionbar!!.title = "Şehir Seç"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        recyclerAdapters()

        recyclerViewCountryListRecyclerView.adapter =
            AppRecyclerViewAdapter(parseJsonStringToNewsList(), recyclerViewItemClickListener)

        if (searchItem != null){
//            val searchView =searchItem.actionView as SearchView
            searchItem.addTextChangedListener(object :TextWatcher{
                override fun afterTextChanged(p0: Editable?) {

                }
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0!!.toString().isNotEmpty()) {
                        cityList.clear()
                        val search = p0.toString().toLowerCase()
                        cityList.forEach {
                            val city = it as City

                            if (city.name!!.toLowerCase().contains(search))
                                cityList.add(city)
                            recyclerViewCountryListRecyclerView.adapter!!.notifyDataSetChanged()
                        }
                    }else {
                        cityList.clear()
                        recyclerViewCountryListRecyclerView.adapter!!.notifyDataSetChanged()
                    }
                }
            })
        }
    }

    fun recyclerAdapters(){
        recyclerViewCountryListRecyclerView = findViewById(R.id.recyclerViewCountryList)
        recyclerViewCountryAdapter = AppRecyclerViewAdapter(countryAddList,recyclerViewItemClickListener)
        recyclerViewCountryListRecyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewCountryListRecyclerView.adapter = recyclerViewCountryAdapter
    }

    private fun parseJsonStringToNewsList(): ArrayList<BaseModel> {

        val jsonString: String = readJsonFromKotlinFile()
        val locationArray = JSONArray(jsonString)

        for (i in 0 until locationArray.length()) {
            var locationObject = locationArray.getJSONObject(i)
            var name = locationObject.getString("name")
            var coord = locationObject.getJSONObject("coord")
            var lat = coord.getString("lat")
            var lon = coord.getString("lon")
            var city = City("", "", "")

            city.lon = lon
            city.lat = lat
            city.name = name

            cityList.add(city)
        }
        return cityList
    }

    private fun readJsonFromKotlinFile(): String {
        var inputString = ""
        try {
            val inputStream: InputStream = assets.open("city_list.json")
            inputString = inputStream.bufferedReader().use { it.readText() }
            Log.d("CountryListActivity", inputString)
        } catch (e: Exception) {
            Log.d("CountryListActivity", e.toString())
        }
        return inputString
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}