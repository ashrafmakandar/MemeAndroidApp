package com.ashraf.memeandroidapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ashraf.memeandroidapp.R
import com.ashraf.memeandroidapp.model.Meme
import com.ashraf.memeandroidapp.utils.Memeitem

class memelistadapter(var context: Context,var memes:List<Meme>,var memeitem: Memeitem):RecyclerView.Adapter<memelistadapter.memeviewholder>(){





    class memeviewholder(itemView: View,var memeitem: Memeitem) : RecyclerView.ViewHolder(itemView) {
        var names: TextView
      var image: ImageView
        init {
            names=itemView.findViewById(R.id.names)
            image= itemView.findViewById(R.id.memeimage)
            names.setOnClickListener {
                memeitem.clicked(adapterPosition)
            }
            image.setOnClickListener {
                memeitem.clicked(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): memeviewholder {
        var view= LayoutInflater.from(context).inflate(R.layout.memecard,parent,false)
        return memeviewholder(view,memeitem)
    }

    override fun onBindViewHolder(holder: memeviewholder, position: Int) {
     holder.names.text= memes[position].name
        holder.image.load(memes[position].url){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)

        }
    }

    override fun getItemCount(): Int {
        return memes.size
    }
}