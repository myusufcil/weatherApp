package com.example.weatherapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.AppRecyclerViewAdapter
import com.example.weatherapp.constant.AppConstants.Companion.appID
import com.example.weatherapp.dto.*
import com.example.weatherapp.network.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import kotlin.collections.ArrayList
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.BuildCompat
import com.example.weatherapp.adapter.RecyclerViewClickListener
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.dto.CityDTO
import com.example.weatherapp.model.Content
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    //IconAndHistoryRecyclerView and List
    lateinit var recyclerAdapterIconAndHistory: AppRecyclerViewAdapter
    lateinit var recyclerViewIconAndHistory: RecyclerView

    //today recyclerview
    lateinit var recyclerViewTodayAdapter: AppRecyclerViewAdapter
    lateinit var recyclerViewToday: RecyclerView

    // tomorrow recyclerview
    lateinit var recyclerviewTomorrowAdapter: AppRecyclerViewAdapter
    lateinit var recyclerviewTomorrow: RecyclerView

    //content recyclerview
    lateinit var recyclerViewContentAdapter: AppRecyclerViewAdapter
    lateinit var recyclerViewContent: RecyclerView

    //Lists
    private val historyMainList = mutableListOf<BaseModel>()
    private val todayMainList = mutableListOf<BaseModel>()
    private val tomorrowMainList = mutableListOf<BaseModel>()
    private val contentMainList = mutableListOf<BaseModel>()


    val LIGHT_MODE = "light"
    val DARK_MODE = "dark"

    private val TAG: String = "MainActivity"
    private lateinit var linearLayoutManager: LinearLayoutManager

    var recyclerViewItemClickListener = object :
        RecyclerViewClickListener {
        override fun onClickListener(position: Int, model: BaseModel) {

            if (model is CityDTO) {
                //Click Listener
                Toast.makeText(this@MainActivity, model.name, Toast.LENGTH_LONG).show()
                getApiInformation(model.lat + "," + model.lon)
                recyclerViewLocation.adapter?.notifyDataSetChanged()
            }

        }

        override fun onLongClickListener(position: Int, model: BaseModel) {
            //TODO Long Click Listener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //      applyTheme(DARK_MODE)
        //   2   anlık saat değerini çağırıyoruz.


        var date = System.currentTimeMillis()
        var formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
        var dateString = formatter.format(Date(date))
        Log.d("SAD", dateString)
        val calendar = Calendar.getInstance()
        calendar.time = Date(date)
        var dateHour = calendar.get(Calendar.HOUR_OF_DAY)
        Log.d("asd", dateHour.toString())

        if (dateHour < 7 || dateHour >= 20)
            applyTheme(DARK_MODE)
        else
            applyTheme(DARK_MODE)

        setContentView(R.layout.activity_main)
        getRecyclerViewAdapter()
        getApiInformation("")
        recyclerViewLocation.layoutManager = linearLayoutManager
        recyclerViewLocation.adapter =
            AppRecyclerViewAdapter(parseJsonStringToNewsList(), recyclerViewItemClickListener)

        countryAddImageButton()

        val resId = R.anim.layout_animation_left_to_right
        val animation = AnimationUtils.loadLayoutAnimation(this, resId)
        recyclerViewToday.setLayoutAnimation(animation)

        val controller: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_left_to_right)

        recyclerViewToday.setLayoutAnimation(controller)
        recyclerViewToday.getAdapter()?.notifyDataSetChanged()
        recyclerViewToday.scheduleLayoutAnimation()
    }

    fun countryAddImageButton() {
        addCountryImageButton.setOnClickListener {
            val intent = Intent(this, CountryListActivity::class.java)
            startActivityForResult(intent, 1001)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1001 -> {
                data?.let {
                    getApiInformation(data!!.getStringExtra("id"))
                }
            }
        }
    }

    private fun applyTheme(@NonNull themePref: String) {
        when (themePref) {
            LIGHT_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            DARK_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                if (BuildCompat.isAtLeastQ()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }//tema saati fonksiyonu

    private fun parseJsonStringToNewsList(): ArrayList<BaseModel> {

        val jsonString: String = readJsonFromKotlinFile()
        val cityList: ArrayList<BaseModel> = arrayListOf()
        val locationArray = JSONArray(jsonString)

        for (i in 0..2) {
            var locationObject = locationArray.getJSONObject(i)
            var name = locationObject.getString("name")
            var coord = locationObject.getJSONObject("coord")
            var lat = coord.getString("lat")
            var lon = coord.getString("lon")
            var city = CityDTO("", "", "")

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
            Log.d(TAG, inputString)
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
        return inputString
    }
//aksdj
    fun getApiInformation(latlon: String) {
        val apiService =
            RetrofitFactory.create().getWeatherByDate(appID, latlon)   //QUERYleri girdiğimiz yer.

        apiService.enqueue(object : Callback<Content> {
            override fun onFailure(call: Call<Content>, t: Throwable) {
                Log.d("Mission", "Failed")
            }

            override fun onResponse(call: Call<Content>, response: Response<Content>) {
                response.body()?.let { contentArea  ->

                    historyMainList.clear()
                    todayMainList.clear()
                    tomorrowMainList.clear()
                    contentMainList.clear()

                    var allList = ContentDTO(
                        contentArea.currently.time,
                        contentArea.currently.summary,
                        contentArea.currently.icon,
                        contentArea.currently.temperature
                    )
                    contentMainList.add(allList)

                    recyclerViewContentAdapter.notifyDataSetChanged()

                    var historyCounter = 0
                    contentArea.daily.data.forEach { _contentHistoryData ->

                        var historyObje =
                            IconAndHistoryDTO(
                                contentArea.daily.data[historyCounter].time,
                                contentArea.daily.data[historyCounter].icon
                            )
                        historyCounter++
                        historyMainList.add(historyObje)
                    }
                    recyclerAdapterIconAndHistory.notifyDataSetChanged()

                    var todayCounter = 0
                    contentArea.hourly.hourlyData.forEach { contentHourlyData ->
                        while (true) {
                            var TodayObje =
                                TodayDTO(
                                    //Sıralama Çok önemli
                                    contentArea.hourly.hourlyData[todayCounter].time,
                                    contentArea.hourly.hourlyData[todayCounter].icon,
                                    contentArea.hourly.hourlyData[todayCounter].temperature
                                )
                            if (todayCounter == 24)
                                break
                            todayCounter++
                            todayMainList.add(TodayObje)
                        }
                    }
                    recyclerViewTodayAdapter.notifyDataSetChanged()

                    var tomorrowCounter = 0
                    contentArea.hourly.hourlyData.takeLast(24)
                        .forEach { contentTomorrowHourlyData ->
                            var TomorrowObje =
                                TomorrowDTO(
                                    contentArea.hourly.hourlyData[tomorrowCounter].time,
                                    contentArea.hourly.hourlyData[tomorrowCounter].icon,
                                    contentArea.hourly.hourlyData[tomorrowCounter].temperature
                                )
                            tomorrowCounter++
                            tomorrowMainList.add(TomorrowObje)
                        }
                    recyclerviewTomorrowAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun getRecyclerViewAdapter() {
        //location kısmını yatay yapıyor.
        linearLayoutManager = LinearLayoutManager(
            this@MainActivity
            , LinearLayoutManager.HORIZONTAL
            , false
        )

        //IconAndHistoryrecyclerview
        recyclerViewIconAndHistory = findViewById(R.id.recyclerViewDate)
        recyclerAdapterIconAndHistory =
            AppRecyclerViewAdapter(historyMainList, recyclerViewItemClickListener)
        recyclerViewIconAndHistory.layoutManager = LinearLayoutManager(
            this@MainActivity
            , LinearLayoutManager.HORIZONTAL
            , false
        )
        recyclerViewIconAndHistory.adapter = recyclerAdapterIconAndHistory

        //recyclerviewToday
        recyclerViewToday = findViewById(R.id.recyclerViewToday)
        recyclerViewTodayAdapter =
            AppRecyclerViewAdapter(todayMainList, recyclerViewItemClickListener)
        recyclerViewToday.layoutManager = LinearLayoutManager(
            this@MainActivity
            , LinearLayoutManager.HORIZONTAL
            , false
        )
        recyclerViewToday.adapter = recyclerViewTodayAdapter

        //recyclerviewTomorrow
        recyclerviewTomorrow = findViewById(R.id.recyclerViewTomorrow)
        recyclerviewTomorrowAdapter =
            AppRecyclerViewAdapter(tomorrowMainList, recyclerViewItemClickListener)
        recyclerviewTomorrow.layoutManager = LinearLayoutManager(
            this@MainActivity
            , LinearLayoutManager.HORIZONTAL
            , false
        )
        recyclerviewTomorrow.adapter = recyclerviewTomorrowAdapter

        //recyclerviewContent
        recyclerViewContent = findViewById(R.id.recyclerViewContent)
        recyclerViewContentAdapter =
            AppRecyclerViewAdapter(contentMainList, recyclerViewItemClickListener)
        recyclerViewContent.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerViewContent.adapter = recyclerViewContentAdapter
    }
}

