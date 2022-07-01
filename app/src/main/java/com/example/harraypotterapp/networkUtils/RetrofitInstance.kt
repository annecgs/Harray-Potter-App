package com.example.harraypotterapp.networkUtils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        fun get(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(com.example.harraypotterapp.utils.Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}