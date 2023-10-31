package com.ramces.ricknmortyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramces.ricknmortyapp.data.api.CharacterApi
import com.ramces.ricknmortyapp.service.RickAndMortyApi
import com.ramces.ricknmortyapp.utils.CharacterFilter
import com.ramces.ricknmortyapp.utils.RickAndMortyStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ListViewModel : ViewModel() {

    private val _status = MutableLiveData<RickAndMortyStatus>()
    val status: LiveData<RickAndMortyStatus>
        get() = _status

    val dataAllList = arrayListOf<CharacterApi>()

    val loadPage = MutableLiveData<Boolean>()


    private val _rickAndMortyData = MutableLiveData<ArrayList<CharacterApi>>()
    val rickAndMortyData: LiveData<ArrayList<CharacterApi>>
        get() = _rickAndMortyData

    private val _navigateToSelectedProperty = MutableLiveData<CharacterApi>()

    val navigateToSelectedProperty: LiveData<CharacterApi>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        coroutineScope.launch {
            getData(1)
        }
    }

    private fun getRickAndMortyData(page: Int) {
        coroutineScope.launch {
            try {
                if (page == 1) {
                    _status.value = RickAndMortyStatus.LOADING
                }
                if (page <= 34) {
                    var getRickAndMorty = RickAndMortyApi.retrofitService.getData(page)
                    if (getRickAndMorty.isSuccessful && getRickAndMorty.body() != null) {
                        val dataRickAndMorty = getRickAndMorty.body()
                        if (dataRickAndMorty!!.results!!.isNotEmpty()) {
                            _status.value = RickAndMortyStatus.DONE
                            dataAllList.addAll(dataRickAndMorty.results)
                            _rickAndMortyData.value = dataAllList
                            loadPage.value = true
                        }
                    } else {
                        _status.value = RickAndMortyStatus.ERROR
                        _rickAndMortyData.value = ArrayList()
                    }
                }
            } catch (e: Exception) {
                _status.value = RickAndMortyStatus.ERROR
                _rickAndMortyData.value = ArrayList()
            }
        }
    }

    private suspend fun getData(page: Int) {
        try {
            if (page == 1) {
                _status.value = RickAndMortyStatus.LOADING
            }
            if (page <= 34) {
                val response = RickAndMortyApi.retrofitService.getData(page)
                if (response.isSuccessful) {
                    val dataRickAndMorty = response.body()
                    if (dataRickAndMorty!!.results!!.isNotEmpty()) {
                        _status.value = RickAndMortyStatus.DONE
                        dataAllList.addAll(dataRickAndMorty.results)
                        _rickAndMortyData.value = dataAllList
                        loadPage.value = true
                    }
                } else {
                    _status.value = RickAndMortyStatus.ERROR
                    _rickAndMortyData.value = ArrayList()
                }
            }

        } catch (e: UnknownHostException) {
            _status.value = RickAndMortyStatus.ERROR
            _rickAndMortyData.value = ArrayList()
        } catch (e: SocketTimeoutException) {
            _status.value = RickAndMortyStatus.ERROR
            _rickAndMortyData.value = ArrayList()
        } catch (e: Exception) {
            _status.value = RickAndMortyStatus.ERROR
            _rickAndMortyData.value = ArrayList()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(rickAndMorty: CharacterApi) {
        _navigateToSelectedProperty.value = rickAndMorty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun updateFilter(filter: CharacterFilter) {}

    fun updatePage(page: Int) {
        loadPage.value = false
        coroutineScope.launch {
            getData(page)
        }
    }
}