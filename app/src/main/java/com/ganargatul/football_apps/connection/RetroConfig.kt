package com.ganargatul.football_apps.connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroConfig {
    val BASE_URL: String = "https://www.thesportsdb.com/api/v1/json/1/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun servicess(): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
    val services: ApiServices = retrofit.create(ApiServices::class.java)
}