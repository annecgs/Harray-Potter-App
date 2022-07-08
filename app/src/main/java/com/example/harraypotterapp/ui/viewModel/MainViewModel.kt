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

class MainViewModel(private val iHogwartsRepository: IHogwartsRepository) : ViewModel() {

    private val _personagensItem = MutableLiveData<PersonagemApiResult<List<PersonagensItem>>>()
    val personagemItem: LiveData<PersonagemApiResult<List<PersonagensItem>>> = _personagensItem

    private val _personagemSelected = MutableLiveData<PersonagensItem>()
    val personagemSelected: LiveData<PersonagensItem> = _personagemSelected

    var personagemFromApi: List<PersonagensItem> = ArrayList()

    // tratamento de erros
    var mensagem = ""

    fun setPersonagens(personagemId: String) {
        viewModelScope.launch {
            _personagensItem.value = PersonagemApiResult.Loading()
            try {
                if (personagemFromApi.isNullOrEmpty()) {
                    personagemFromApi = withContext(Dispatchers.IO) {
                        iHogwartsRepository.getPersonagens()
                    }
                }
                _personagemSelected.value = personagemFromApi.find { it.name == personagemId }
                _personagensItem.value = PersonagemApiResult.Success(personagemFromApi)
            } catch (e: Exception) {
                val personagemResult = PersonagemApiResult.Error<List<PersonagensItem>>(e)
                _personagensItem.value = personagemResult
            }
        }
    }

    fun getPersonagensFromRetrofit() {
        viewModelScope.launch {
            _personagensItem.value = PersonagemApiResult.Loading()
            try {
                if (personagemFromApi.isNullOrEmpty()) {
                    personagemFromApi = withContext(Dispatchers.IO) {
                        iHogwartsRepository.getPersonagens()
                    }
                }
                _personagensItem.value = PersonagemApiResult.Success(personagemFromApi)
            } catch (e: Exception) {
                val personagemResult = PersonagemApiResult.Error<List<PersonagensItem>>(e)
                Log.d("PersonagemResult", "Personagem:  ${personagemResult.throwable.message}")
                _personagensItem.value = personagemResult
            }
        }
    }

    fun setFavorite(isFavorite: Boolean) {
        viewModelScope.launch {
            _personagemSelected.value?.isFavorite = isFavorite
        }
    }
}
