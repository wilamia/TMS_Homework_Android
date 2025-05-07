package com.example.studyingproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.studyingproject.domain.AddNoteUseCase
import com.example.studyingproject.domain.GetNotesUseCase
import com.example.studyingproject.data.Note
import com.example.studyingproject.presentation.NoteRepository
import com.example.studyingproject.domain.UpdateNoteUseCase
import com.example.studyingproject.presentation.InMemoryNoteRepository

class MainFragmentViewModel(
    repository: NoteRepository = InMemoryNoteRepository()
): ViewModel() {

    private val addNoteUseCase = AddNoteUseCase(repository)
    private val getNotesUseCase = GetNotesUseCase(repository)
    private val updateNoteUseCase = UpdateNoteUseCase(repository)

    val notes = getNotesUseCase.invoke()

    fun addNote(note: Note) = addNoteUseCase.invoke(note)

    fun updateNote(note: Note) = updateNoteUseCase.invoke(note)
}