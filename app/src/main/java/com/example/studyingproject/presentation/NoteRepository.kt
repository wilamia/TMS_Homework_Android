package com.example.studyingproject.presentation

import androidx.lifecycle.LiveData
import com.example.studyingproject.data.Note

interface NoteRepository {
    fun addNote(note: Note)
    fun updateNote(note: Note)
    fun getNotes(): LiveData<List<Note>>
}