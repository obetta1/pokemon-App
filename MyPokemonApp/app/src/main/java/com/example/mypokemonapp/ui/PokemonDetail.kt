package com.example.mypokemonapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.mypokemonapp.R
import com.example.mypokemonapp.connectivity.ConnectivityLiveData
import com.example.mypokemonapp.model.DetailPokeData
import com.example.mypokemonapp.model.PokemonCharacters
import com.example.mypokemonapp.network.RetroInstance.Companion.id
import com.example.mypokemonapp.recyclerview.RecyclerListFragment
import com.example.mypokemonapp.viewModel.DetailViewModel
import com.example.mypokemonapp.viewModel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import java.util.*

class PokemonDetail : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var features: TextView
    private lateinit var features2: TextView
    private lateinit var statusImage: ImageView
    private lateinit var status: TextView
    private lateinit var pokemonCharacter: PokemonCharacters

    private lateinit var connectivityLiveData: ConnectivityLiveData


    // private lateinit var binding : PokemonDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        var name: TextView = findViewById(R.id.name)
        var main_poke_image: ImageView = findViewById(R.id.main_photo_image)
        features = findViewById(R.id.features)

       // val connet = ConnectivityLiveData(this.application)
        connectivityLiveData = ConnectivityLiveData(this.application)

// this the values passed from the recyclerList activity
        var bundle: Bundle? = intent.extras
        val detail_name = intent.getStringExtra("name")
        val detail_url = intent.getStringExtra("url")

        // call the networkObserver to check when there is network or not
        // before making call to the API
        networkObserver(detail_url!!)

        name.text = detail_name

        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.viewModelScope
        // this is the base url
        //this is responsible for getting the image from the api
        val image_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        val url = detail_url.toString()
        var str = url.split("/")
        id = str[str.lastIndex - 1].toInt()

        Glide.with(this).load("$image_url$id.png")
            .centerCrop()
            .placeholder(R.drawable.color_background)
            .into(main_poke_image)
        

        // call the view model function makeApi call
       // viewModel.makeApiCall(detail_url!!)

        viewModel.pokemon.observe(this, Observer<DetailPokeData> {
            if (it != null) {
                features.text = getString(
                    R.string.features_text,
                    it.abilities[0].ability.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    it.abilities[0].isHidden.toString(),
                    it.abilities[0].slot.toString(),
                    it.abilities[1].ability.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    it.abilities[1].isHidden.toString(),
                    it.abilities[1].slot.toString(),
                    it.baseExperience.toString()
                )




            } else {
                Toast.makeText(this, "Error occurred in getting data", Toast.LENGTH_LONG).show()
                var intent = Intent(this, RecyclerListFragment::class.java)
                startActivity(intent)
            }
        })

    }


    fun networkObserver( detail_url: String){
        //1
        connectivityLiveData.observe(this, Observer { isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    //3
                    val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
                    viewModel.makeApiCall(detail_url!!)
//                statusButton.visibility = View.GONE
//                moviesRecyclerView.visibility = View.VISIBLE
//                searchEditText.visibility = View.VISIBLE
                }
                false -> {
                    Toast.makeText(this, "network failed", Toast.LENGTH_SHORT).show()

//                statusButton.visibility = View.VISIBLE
//                moviesRecyclerView.visibility = View.GONE
//                searchEditText.visibility = View.GONE
                }
            }
        })

    }
}