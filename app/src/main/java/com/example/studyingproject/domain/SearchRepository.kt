package com.example.studyingproject.domain

interface SearchRepository {
    fun search(query: String): io.reactivex.Observable<List<String>>
}