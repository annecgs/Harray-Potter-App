package com.example.harraypotterapp.data.repository

import com.example.harraypotterapp.data.remote.dto.PersonagensItem

interface IHogwartsRepository {
    suspend fun getPersonagens(): List<PersonagensItem>
}