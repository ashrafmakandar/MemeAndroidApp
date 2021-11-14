package com.ashraf.memeandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashraf.memeandroidapp.model.Meme
import com.ashraf.memeandroidapp.network.MemeApi
import com.ashraf.memeandroidapp.repo.MemeRepo
import com.ashraf.memeandroidapp.ui.memelistadapter
import com.ashraf.memeandroidapp.utils.MemeConstants
import com.ashraf.memeandroidapp.utils.Memeitem
import com.ashraf.memeandroidapp.viewmodel.MemeViewModel
import com.ashraf.memeandroidapp.viewmodel.MemeViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), Memeitem {
    lateinit var memeViewModel: MemeViewModel
    lateinit var adapter: memelistadapter
    lateinit var recyclerView: RecyclerView
    lateinit var listmeme: List<Meme>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val memeApi = MemeConstants.getinstance().create(MemeApi::class.java)
        val repo = MemeRepo(memeApi)
        recyclerView = findViewById(R.id.recylermemeview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        memeViewModel =
            ViewModelProvider(this, MemeViewModelFactory(repo)).get(MemeViewModel::class.java)


        memeViewModel.memes.observe(this@MainActivity, {

            Handler(Looper.getMainLooper()).post {
                listmeme = it.data.memes
                adapter = memelistadapter(applicationContext, listmeme, this@MainActivity)
                recyclerView.adapter = adapter

            }
        })


    }

    override fun clicked(position: Int) {
        Toast.makeText(this, listmeme[position].name, Toast.LENGTH_SHORT).show()

    }
}