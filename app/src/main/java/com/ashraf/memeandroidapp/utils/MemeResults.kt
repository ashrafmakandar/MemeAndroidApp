package com.ashraf.memeandroidapp.utils

import com.ashraf.memeandroidapp.model.MemesResponse

sealed class MemeResults<out T>{

    data class Loading<out View>(var loading: Int): MemeResults<View>()
    data class Memedata<MemesResponse>(var memes:MemesResponse): MemeResults<MemesResponse>()
    data class Error<out String>(var error:kotlin.String): MemeResults<String>()
}
