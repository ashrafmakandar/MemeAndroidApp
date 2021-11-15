package com.ashraf.memeandroidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashraf.memeandroidapp.model.Meme
import com.ashraf.memeandroidapp.network.MemeApi
import com.ashraf.memeandroidapp.repo.MemeRepo
import com.ashraf.memeandroidapp.ui.DetailActivity
import com.ashraf.memeandroidapp.ui.memelistadapter
import com.ashraf.memeandroidapp.utils.MemeConstants
import com.ashraf.memeandroidapp.utils.MemeResults
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
    lateinit var progressBar: ProgressBar
    lateinit var listmeme: List<Meme>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val memeApi = MemeConstants.getinstance().create(MemeApi::class.java)
        val repo = MemeRepo(memeApi)
        recyclerView = findViewById(R.id.recylermemeview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        progressBar= findViewById(R.id.progressBar)

        memeViewModel =
            ViewModelProvider(this, MemeViewModelFactory(repo)).get(MemeViewModel::class.java)


        memeViewModel.memes.observe(this,{
            when(it){
                is MemeResults.Loading->{
                    progressBar.visibility= it.loading
                }
                is MemeResults.Memedata->{
                    listmeme = it.memes.data.memes
                    adapter = memelistadapter(applicationContext, listmeme, this@MainActivity)
                    progressBar.visibility= View.GONE
                    recyclerView.adapter = adapter
                }
                is MemeResults.Error->{
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this,it.error,Toast.LENGTH_SHORT).show()
                    }

                }
            }
        })



    }

    override fun clicked(position: Int) {
        Toast.makeText(this, listmeme[position].name, Toast.LENGTH_SHORT).show()
        var intent=Intent(this,DetailActivity::class.java)
        intent.putExtra("meme",listmeme[position])
        startActivity(intent)

    }
}