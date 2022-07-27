package com.example.frontend.view.viewModel

import com.example.backend.data.remote.IHogwartsClient
import com.example.backend.data.remote.dto.PersonagensItem

class IHogwartsClientFake(val string: String) : IHogwartsClient {
    override suspend fun getData(): List<PersonagensItem> {
        return if (string == "Ok-200") {
            mockPersonagemItem
        } else if (string == "ERRO-GENERIC") {
            throw Throwable(string)
        } else {
            listOf()
        }
    }
}

val mockPersonagemItem = listOf(
    PersonagensItem(
        "Daniel Radcliffe",
        true,
        "half-blood",
        "31-07-1980",
        "green",
        "male",
        "black",
        false,
        true,
        "Gryffindor",
        "http://hp-api.herokuapp.com/images/harry.jpg",
        "Harry Potter",
        "stag",
        "human",
        true,
        "1980",
        false
    )
)
