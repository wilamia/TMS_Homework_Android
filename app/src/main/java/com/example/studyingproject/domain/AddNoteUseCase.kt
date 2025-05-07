package com.example.studyingproject.domain

import com.example.studyingproject.data.Note
import com.example.studyingproject.presentation.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {
    fun invoke(note: Note) = repository.addNote(note)
}
