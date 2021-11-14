package com.ashraf.memeandroidapp.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("memes")
    val memes: List<Meme>
)