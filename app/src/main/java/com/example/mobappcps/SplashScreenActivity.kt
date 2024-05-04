package com.example.mobappcps

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
        }, 4000)

        }
    }
