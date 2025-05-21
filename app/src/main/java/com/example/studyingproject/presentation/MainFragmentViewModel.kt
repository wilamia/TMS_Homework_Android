package com.example.studyingproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.data.PokemonApiService
import com.example.studyingproject.data.PokemonRepositoryImpl
import com.example.studyingproject.data.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.random.nextInt

class MainFragmentViewModel: ViewModel() {

    private lateinit var repository: PokemonRepositoryImpl
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val pageSize = Random.nextInt(5..100)
    fun init(apiKey: String) {
        repository = PokemonRepositoryImpl(PokemonApiService(apiKey))
    }

    fun loadPokemon() {
        viewModelScope.launch {
            try {
                val result = repository.getPokemonList(pageSize)
                _pokemonList.value = result
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {

                }
            }
        }
    }
}
