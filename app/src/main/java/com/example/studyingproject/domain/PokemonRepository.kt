package com.example.studyingproject.domain

import com.example.studyingproject.data.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(pageSize: Int): List<Pokemon>
}