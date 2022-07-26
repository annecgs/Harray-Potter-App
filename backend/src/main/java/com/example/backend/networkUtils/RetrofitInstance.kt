package com.example.backend.networkUtils

import com.example.backend.utils.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {
    companion object {
        fun get(): Retrofit {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()

            return retrofit

            // return Retrofit.Builder()
            //     .baseUrl(com.example.backend.utils.Constants.BASE_URL)
            //   .addConverterFactory(GsonConverterFactory.create())
            //  .build()
        }
    }
}
