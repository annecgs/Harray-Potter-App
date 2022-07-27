package com.example.frontend.view.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.backend.data.remote.dto.PersonagemApiResult
import com.example.backend.data.repository.PersonagensRepository
import com.example.frontend.test_utils.MainCoroutineRule
import com.example.frontend.test_utils.getOrAwaitValue
import com.example.frontend.ui.viewModel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var mainViewModelOk: MainViewModel
    lateinit var mainViewModelError: MainViewModel

    @Before
    fun setup() {
        mainViewModelOk = MainViewModel(
            PersonagensRepository(
                IHogwartsClientFake("OK-200")
            )
        )
        mainViewModelError = MainViewModel(
            PersonagensRepository(
                IHogwartsClientFake("ERRO-Generic")
            )
        )
    }

    @Test
    fun `Set ID of _personagemSelected and return the same ID in personagemSelected`() {
        // Given
        val id = "Harry Potter"

        // Then
        mainViewModelOk.setPersonagens(id)
        val result = mainViewModelOk.personagemSelected.getOrAwaitValue().name

        // When
        Assert.assertEquals(id, result)
    }

    @Test
    fun `When the list of personagens isn't empty, not require other api call`() {
        // Given
        val list = mainViewModelOk.personagemItem
        var result = true
        var result2 = true

        // Then
        mainViewModelOk.getPersonagensFromRetrofit()
        when (list) {
            is PersonagemApiResult.Success<*> -> {
                result = list.getOrAwaitValue().toArray().isEmpty() // Return False
            }
        }

        mainViewModelOk.getPersonagensFromRetrofit()

        when (mainViewModelOk.personagemItem) {
            is PersonagemApiResult.Success<*> -> {
                result2 = list.getOrAwaitValue().toArray().isEmpty() // Return False
            }
        }

        // When
        Assert.assertEquals(result, result2)
    }

    @Test
    fun `When setFavorite set true`() {
        // Given
        val personagem = mockPersonagemItem[0]
        personagem.isFavorite = true // Is favorite

        // When
        mainViewModelOk.getPersonagensFromRetrofit()
        mainViewModelOk.setPersonagens("Harry Potter")
        mainViewModelOk.setFavorite(true)

        // Then

        Assert.assertEquals(
            personagem.isFavorite,
            mainViewModelOk.personagemSelected.getOrAwaitValue().isFavorite
        )
    }

    @Test
    fun `When setFavorite set false`() {
        // Given
        val personagem = mockPersonagemItem[0]
        personagem.isFavorite = false // Is favorite

        // When
        mainViewModelOk.getPersonagensFromRetrofit()
        mainViewModelOk.setPersonagens("Harry Potter")
        mainViewModelOk.setFavorite(false)

        // Then

        Assert.assertEquals(
            personagem.isFavorite,
            mainViewModelOk.personagemSelected.getOrAwaitValue().isFavorite
        )
    }

}