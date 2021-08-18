package com.example.mypokemonapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// this is the retrofit class
class RetroInstance() {

    companion object {
        var id = 0
        val baseUrl = "https://pokeapi.co/api/v2/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        fun getRetroInstanceDetail(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}