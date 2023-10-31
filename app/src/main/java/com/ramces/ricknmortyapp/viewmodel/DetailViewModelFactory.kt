package com.ramces.ricknmortyapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramces.ricknmortyapp.data.api.CharacterApi

class DetailViewModelFactory(
    private val rickAndMorty: CharacterApi,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(rickAndMorty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}