package com.example.frontend.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.frontend.R
import com.example.frontend.ui.activity.MainActivity

class OnboardingActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        var go = findViewById<Button>(R.id.start)
        go.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        viewPager = findViewById(R.id.idViewPager)

        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.onboading_01_1
        imageList = imageList + R.drawable.onboading_02
        imageList = imageList + R.drawable.onboading_03
        imageList = imageList + R.drawable.onboading_04

        viewPagerAdapter = ViewPagerAdapter(this@OnboardingActivity, imageList)

        viewPager.adapter = viewPagerAdapter


    }
}