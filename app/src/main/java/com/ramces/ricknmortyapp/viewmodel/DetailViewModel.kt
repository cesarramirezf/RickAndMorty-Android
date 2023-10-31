package com.ramces.ricknmortyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ramces.ricknmortyapp.data.api.CharacterApi

class DetailViewModel(@Suppress("UNUSED_PARAMETER") rickAndMorty: CharacterApi, app: Application) :
    AndroidViewModel(app) {

    private val _selectedItem = MutableLiveData<CharacterApi>()
    val selectedItem: LiveData<CharacterApi>
        get() = _selectedItem

    init {
        _selectedItem.value = rickAndMorty
    }
}