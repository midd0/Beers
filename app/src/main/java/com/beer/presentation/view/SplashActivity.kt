package com.beer.presentation.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mido.beers.R
import dagger.hilt.android.AndroidEntryPoint

const val MINIMUM_SPLASH_TIME = 2000L

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        timeSplash()
    }

    private fun timeSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, MINIMUM_SPLASH_TIME)
    }
}