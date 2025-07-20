package com.example.studyingproject.domain

class SearchUseCase(private val repository: SearchRepository) {
    fun execute(query: String) = repository.search(query)
}