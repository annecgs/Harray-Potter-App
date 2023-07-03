package com.example.frontend.ui.activity.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frontend.R
import com.example.frontend.ui.activity.MainActivity
import com.example.frontend.ui.onboarding.OnboardingActivity

class SplashScreen : AppCompatActivity() {
    private val time: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        android.os.Handler().postDelayed({
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }, time)
    }
}
