package com.ashraf.memeandroidapp.repo

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashraf.memeandroidapp.model.MemesResponse
import com.ashraf.memeandroidapp.network.MemeApi
import com.ashraf.memeandroidapp.utils.MemeResults

class MemeRepo(val memeApi: MemeApi) {
    val memesdata=MutableLiveData<MemeResults<MemesResponse>>()
    val memes: LiveData<MemeResults<MemesResponse>>
    get() = memesdata

    suspend fun getmemes(){
        var data= memeApi.getmemes()
        memesdata.postValue(MemeResults.Loading(
            View.VISIBLE
        ))
        if (data.isSuccessful&&data.body()!=null)
        {
            memesdata.postValue(MemeResults.Loading(
                View.GONE
            ))
            memesdata.postValue(MemeResults.Memedata(
                data.body()!!
            ))

        }
        else{
            memesdata.postValue(MemeResults.Loading(
                View.GONE
            ))
            memesdata.postValue(
                MemeResults.Error("some error")
            )


        }
    }
}