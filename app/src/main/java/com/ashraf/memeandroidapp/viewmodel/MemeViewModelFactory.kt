package com.ashraf.memeandroidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashraf.memeandroidapp.repo.MemeRepo

class MemeViewModelFactory(var repo: MemeRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
     return MemeViewModel(repo) as T
    }
}