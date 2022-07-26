package com.example.backend.data.remote.dto

sealed class PersonagemApiResult<T> {
    class Loading<T> : PersonagemApiResult<T>()
    class Success<T>(val data: T) : PersonagemApiResult<T>()
    class Error<T>(val throwable: Throwable) : PersonagemApiResult<T>()
}
