package com.ashraf.memeandroidapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashraf.memeandroidapp.model.MemesResponse
import com.ashraf.memeandroidapp.network.MemeApi

class MemeRepo(val memeApi: MemeApi) {
    val memesdata=MutableLiveData<MemesResponse>()
    val memes: LiveData<MemesResponse>
    get() = memesdata

    suspend fun getmemes(){
        var data= memeApi.getmemes()
        if (data.isSuccessful&&data.body()!=null)
        {
            memesdata.postValue(data.body())
        }
    }
}