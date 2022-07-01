package com.example.harraypotterapp.data.remote

import com.example.harraypotterapp.data.remote.dto.PersonagensItem
import retrofit2.http.GET

interface IHogwartsClient {
    @GET("characters")
    suspend fun getData(): List<PersonagensItem>
}
