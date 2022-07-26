package com.example.backend.data.repository

import com.example.backend.data.remote.IHogwartsClient
import com.example.backend.data.remote.dto.PersonagensItem
import com.example.backend.data.repository.IHogwartsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonagensRepository(private val hogwartsClient: IHogwartsClient) : IHogwartsRepository {
    override suspend fun getPersonagens(): List<PersonagensItem> {
        return withContext(Dispatchers.IO) {
            // hogwartsClient.getData().body()!!.personagens
            hogwartsClient.getData()
        }
    }
}
