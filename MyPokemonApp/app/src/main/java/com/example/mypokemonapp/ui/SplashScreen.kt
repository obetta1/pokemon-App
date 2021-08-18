package com.example.mypokemonapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.mypokemonapp.R
import com.google.android.material.textview.MaterialTextView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val text2:MaterialTextView = findViewById(R.id.welcomeText)
        val text1:MaterialTextView = findViewById(R.id.welcomeText2)
        val text3:MaterialTextView = findViewById(R.id.welcomeText3)

        val topText = AnimationUtils.loadAnimation(this, R.anim.anim)
        val bottomText = AnimationUtils.loadAnimation(this, R.anim.bottom)
        val middleText = AnimationUtils.loadAnimation(this, R.anim.middle)

        text1.startAnimation(topText)
        text2.startAnimation(middleText)
        text3.startAnimation(bottomText)

        val splashScreenTimeOut = 4000
        var intent = Intent(this, MainActivity::class.java)

// use the handler to delay the activity of the splash screen
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, splashScreenTimeOut.toLong())
    }
}