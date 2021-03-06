package com.example.backend.data.remote

import com.example.backend.data.remote.dto.PersonagensItem
import retrofit2.http.GET

interface IHogwartsClient {
    @GET("characters")
    // suspend fun getData(): Response<Personagens>
    // suspend fun getData(): List<PersonagensItem>
    suspend fun getData(): List<PersonagensItem>
}
