package com.ramces.ricknmortyapp.service

import com.ramces.ricknmortyapp.data.api.DataResponseApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Service Api Rick And Morty
private const val BASE_URL = "https://rickandmortyapi.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RickAndMortyApiService {

    @GET("character/")
    suspend fun getData(@Query("page") type: Int):
            Response<DataResponseApi>
}

object RickAndMortyApi {
    val retrofitService : RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }
}