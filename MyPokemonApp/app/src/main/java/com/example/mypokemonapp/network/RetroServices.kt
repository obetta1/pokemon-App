package com.example.mypokemonapp.network

import android.widget.AbsListView
import com.example.mypokemonapp.model.DetailPokeData
import com.example.mypokemonapp.model.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetroServices  {
    //  this is to get api so we need to define the repository
    //define the suspend function to be use with the coroutine
    //with the Quarry parameters

    @GET("pokemon?limit=200&offset=0")
    suspend fun getDataFromApi(@Query("q") query: String): RecyclerList

    @GET
    suspend fun getPokemonDetailFromApi(@Url url: String): DetailPokeData

}