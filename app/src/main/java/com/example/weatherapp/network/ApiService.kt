package com.example.weatherapp.network

import com.example.weatherapp.dto.Content
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    //APIKEY
    @GET("{key}/{latlon}")
    fun getWeatherByDate(@Path("key") keyName: String, @Path("latlon") latlon: String): Call<Content>

//    @Headers(
//        "x-rapidapi-host:wft-geo-db.p.rapidapi.com",
//        "x-rapidapi-key:70a25ab5c0msh357f671f7161c7fp1d4e91jsn291b38abe94b"
//    )
//    @GET("cities")
//    fun getCityFromApı(@Query("\$limit") limit: String, @Query("\$offset") offSet: String): Call<CityList>


}