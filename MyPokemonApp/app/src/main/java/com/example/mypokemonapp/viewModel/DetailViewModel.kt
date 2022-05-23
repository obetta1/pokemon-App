package com.example.mypokemonapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokemonapp.connectivity.ConnectivityLiveData
import com.example.mypokemonapp.model.DetailPokeData
import com.example.mypokemonapp.network.RetroInstance
import com.example.mypokemonapp.network.RetroServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel:ViewModel() {
    // Holds the details of the pokemon retrieved from the API
    private val _pokemon = MutableLiveData<DetailPokeData>()
    val pokemon = _pokemon as LiveData<DetailPokeData>

    // Keeps track of exceptions which may occur during network call
    private val _message = MutableLiveData<String>()
    val message = _message as LiveData<String>

    fun getRecyclerListObserver():MutableLiveData<DetailPokeData>{
        return _pokemon
    }
     // Network call to the endpoints with error handling

    fun makeApiCall(url: String){

        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstanceDetail()
                .create(RetroServices::class.java)
            val responce = retroInstance.getPokemonDetailFromApi(url)
            _pokemon.postValue((responce))
        }
    }
}