package com.ashraf.memeandroidapp.network

import com.ashraf.memeandroidapp.model.MemesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MemeApi {

    @GET("/get_memes")
    suspend fun  getmemes():Response<MemesResponse>
}