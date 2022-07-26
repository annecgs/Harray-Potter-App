package com.example.frontend.utils

import com.example.backend.data.remote.IHogwartsClient
import com.example.backend.data.remote.dto.PersonagensItem
import com.example.backend.data.repository.PersonagensRepository
import com.example.backend.networkUtils.RetrofitInstance
import com.example.frontend.ui.viewModel.MainViewModelFactory
import java.util.ArrayList

class Helpers {
    companion object {
        fun getMainViewModelFactory(): MainViewModelFactory {
            val iHogwartsClient: IHogwartsClient by lazy {
                RetrofitInstance.get().create(IHogwartsClient::class.java)
            }
            val personagensRepository = PersonagensRepository(iHogwartsClient)
            return MainViewModelFactory(personagensRepository)
        }

        fun FilterListQuery(text: String?, list: List<PersonagensItem>): MutableList<PersonagensItem> {
            var newList: MutableList<PersonagensItem> = ArrayList()
            list.forEach {
                if (it.name.contains(text.toString(), true)
                ) {
                    newList.add(it)
                }
            }
            return newList
        }
    }
}