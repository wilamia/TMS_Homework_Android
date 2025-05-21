package com.example.studyingproject.data

import com.example.studyingproject.domain.PokemonRepository

class PokemonRepositoryImpl(
    private val apiService: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemonList(pageSize: Int): List<Pokemon> {
        return apiService.fetchPokemonList(pageSize)
    }
}
