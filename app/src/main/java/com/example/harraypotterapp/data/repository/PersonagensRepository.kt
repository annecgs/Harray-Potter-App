package com.example.harraypotterapp.data.repository

import com.example.harraypotterapp.data.remote.IHogwartsClient
import com.example.harraypotterapp.data.remote.dto.PersonagensItem
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
