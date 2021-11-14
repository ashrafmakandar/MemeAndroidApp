package com.ashraf.memeandroidapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.ashraf.memeandroidapp.R
import com.ashraf.memeandroidapp.model.Meme

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val meme = intent.getSerializableExtra("meme") as? Meme
        val textview= findViewById<TextView>(R.id.textViewmeme)
        val imageView= findViewById<ImageView>(R.id.imageViewmeme)
        textview.text= meme?.name
        imageView.load(meme?.url)
    }
}