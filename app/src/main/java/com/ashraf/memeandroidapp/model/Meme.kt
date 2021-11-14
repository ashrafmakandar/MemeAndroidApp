package com.ashraf.memeandroidapp.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meme(
    @SerializedName("box_count")
    val boxCount: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
):Serializable