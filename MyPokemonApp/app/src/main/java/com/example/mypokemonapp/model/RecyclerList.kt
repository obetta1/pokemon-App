package com.example.mypokemonapp.model

import android.os.Parcelable

// RecyclerList = PokemonRequest

 data class RecyclerList ( val count : Int ,
                           val next : Any,
                           val previous:Any,
                           val results: ArrayList<Result> )


data class Result (val name :String, val url:String )

data class Owner(val avatar_url : String?)



