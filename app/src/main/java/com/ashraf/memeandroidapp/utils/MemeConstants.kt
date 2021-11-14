package com.ashraf.memeandroidapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MemeConstants {
    val BASE_URL = "https://api.imgflip.com"

     fun getinstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}