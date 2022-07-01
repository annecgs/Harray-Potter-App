package com.example.harraypotterapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harraypotterapp.data.remote.dto.PersonagemApiResult
import com.example.harraypotterapp.data.remote.dto.PersonagensItem
import com.example.harraypotterapp.data.repository.IHogwartsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel(
    private val iHogwartsRepository: IHogwartsRepository
) : ViewModel() {

    private val _personagenSelected = MutableLiveData<PersonagensItem>()
    val personagenSelected: LiveData<PersonagensItem> = _personagenSelected
    private val _personagensItem = MutableLiveData<PersonagemApiResult<List<PersonagensItem>>>()
    val personagenItem: LiveData<PersonagemApiResult<List<PersonagensItem>>> = _personagensItem
    var personagenFromApi: List<PersonagensItem> = ArrayList()

    // tratamento de erros
    var mensagem = ""

    fun setPersonagen(name: String) {
        viewModelScope.launch {
            _personagensItem.value = PersonagemApiResult.Loading()
            try {
                if (personagenFromApi.isNullOrEmpty()) {
                    personagenFromApi = withContext(Dispatchers.IO) {
                        iHogwartsRepository.getPersonagens()
                    }
                }
                _personagenSelected.value = personagenFromApi.find { it.name == name }
                _personagensItem.value = PersonagemApiResult.Success(personagenFromApi)
            } catch (e: Exception) {
                val coinResult = PersonagemApiResult.Error<List<PersonagensItem>>(e)
                _personagensItem.value = coinResult
            }
        }
    }

    fun getPersonagenFromRetrofit() {
        viewModelScope.launch {
            _personagensItem.value = PersonagemApiResult.Loading()
            try {
                if (personagenFromApi.isNullOrEmpty()) {
                    personagenFromApi = withContext(Dispatchers.IO) {
                        iHogwartsRepository.getPersonagens()
                    }
                }
                _personagensItem.value = PersonagemApiResult.Success(personagenFromApi)
            } catch (e: Exception) {
                val personagemResult = PersonagemApiResult.Error<List<PersonagensItem>>(e)
                Log.d("PersonagenResult", "setPersonagem: $personagemResult")
                _personagensItem.value = personagemResult
            }
        }
    }
}
