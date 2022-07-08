package com.example.harraypotterapp.ui.activity.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.harraypotterapp.ui.activity.MainActivity
import com.example.harraypotterapp.R

class SplashScreen : AppCompatActivity() {
    private val time: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        android.os.Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, time)
    }
}
