package com.example.mypokemonapp.viewModel

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokemonapp.model.RecyclerList
import com.example.mypokemonapp.network.RetroInstance
import com.example.mypokemonapp.network.RetroServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel : ViewModel() {
    var recyclerListLiveData: MutableLiveData<RecyclerList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    //this function returns the result from the API
    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return recyclerListLiveData
    }

    // this is basically the function for the coroutine
    //making the api call in the IO instead of the main thread

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {

            // this is where i handled the error thatb may occurre from the network call
            // using try and catch
            try {
                val retroInstance = RetroInstance.getRetroInstance()
                    .create(RetroServices::class.java)
                val responce = retroInstance.getDataFromApi("ny")
                recyclerListLiveData.postValue((responce))

            } catch (e: UnknownHostException) {
                e.printStackTrace()
            }


        }

    }

}