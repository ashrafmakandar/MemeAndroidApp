package com.ashraf.memeandroidapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashraf.memeandroidapp.model.MemesResponse
import com.ashraf.memeandroidapp.repo.MemeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemeViewModel(var repo: MemeRepo):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getmemes()
        }
    }
    val memes:LiveData<MemesResponse>
    get() = repo.memes
}