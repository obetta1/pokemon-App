package com.example.mypokemonapp.recyclerview

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemonapp.ui.PokemonDetail
import com.example.mypokemonapp.R
import com.example.mypokemonapp.connectivity.ConnectivityLiveData
import com.example.mypokemonapp.model.RecyclerList
import com.example.mypokemonapp.ui.UploadImage
import com.example.mypokemonapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recycler_list.*
import okhttp3.internal.platform.Jdk9Platform.Companion.isAvailable


class RecyclerListFragment : Fragment() {

    private lateinit var recyclerAdapter: RecyclerViewAdapter
    private lateinit var connectivityLiveData: ConnectivityLiveData



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(requireActivity().application)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)
        initViewModel(view)
        return view


    }
    // this function is used to observe the connectivity livedata to check when the network is active
fun observer(){
    //1
    connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
        //2
        when (isAvailable) {
            true -> {
                //3
                val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                viewModel.makeApiCall()
                progressBar.visibility = View.GONE


            }
          false -> {
              Toast.makeText(context, "network failed", Toast.LENGTH_SHORT).show()

               progressBar.visibility = View.VISIBLE
          }
        }
    })
}
 // this function is used to observe the recyclerlistlivedata
    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, {

                if (it != null) {
                    recyclerAdapter = RecyclerViewAdapter()
                    recyclerAdapter.setUpdateData(it.results)
                    recyclerView.adapter = recyclerAdapter

                    //set the upload button
                    uploadImage.setOnClickListener {
                        var intent = Intent(context, UploadImage::class.java)
                        startActivity(intent)
                    }
                    // setting the onclick listener to the items on the  recycler view
                    recyclerAdapter.setOnItemClickListener(object :
                        RecyclerViewAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            var intent = Intent(context, PokemonDetail::class.java).apply {
                                putExtra("name", it.results[position].name)
                                putExtra("url", it.results[position].url)
                            }
                            startActivity(intent)
                        } })

                } else {

               Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
                }

                recyclerAdapter.notifyDataSetChanged()
            })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerListFragment()
    }
}
