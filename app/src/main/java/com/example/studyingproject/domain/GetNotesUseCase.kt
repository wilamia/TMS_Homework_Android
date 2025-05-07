package com.example.studyingproject.domain

import com.example.studyingproject.presentation.NoteRepository

class GetNotesUseCase(private val repository: NoteRepository) {
    fun invoke() = repository.getNotes()
}