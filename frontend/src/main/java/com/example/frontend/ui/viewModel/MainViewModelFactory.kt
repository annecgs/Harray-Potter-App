package com.example.frontend.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.backend.data.repository.IHogwartsRepository

class MainViewModelFactory(private val iHogwartsRepository: IHogwartsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(iHogwartsRepository) as T
    }
}
