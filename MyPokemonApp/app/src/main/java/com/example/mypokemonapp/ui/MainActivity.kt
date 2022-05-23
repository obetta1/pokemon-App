package com.example.mypokemonapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ahmadrosid.svgloader.SvgLoader
import com.example.mypokemonapp.R
import com.example.mypokemonapp.connectivity.ConnectivityLiveData
import com.example.mypokemonapp.recyclerview.RecyclerListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recycler_list.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()

    }

    private fun setupFragment() {
        // det the the instance of the fragment manager
        val fragment = RecyclerListFragment.newInstance()
        val fragmentManager:FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()

    }
}