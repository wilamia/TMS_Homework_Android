package com.example.studyingproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.studyingproject.data.Note
import com.example.studyingproject.domain.AddNoteUseCase
import com.example.studyingproject.domain.GetNotesUseCase
import com.example.studyingproject.presentation.NoteRepository

class MainFragmentViewModel: ViewModel() {

    private val repository = NoteRepository()
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val getNotesUseCase = GetNotesUseCase(repository)

    val notes: LiveData<MutableList<Note>> = getNotesUseCase.invoke()

    fun addNote(note: Note) = addNoteUseCase.invoke(note)

}