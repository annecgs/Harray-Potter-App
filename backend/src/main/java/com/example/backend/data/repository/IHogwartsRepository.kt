package com.example.backend.data.repository

import com.example.backend.data.remote.dto.PersonagensItem

interface IHogwartsRepository {
    suspend fun getPersonagens(): List<PersonagensItem>
}
