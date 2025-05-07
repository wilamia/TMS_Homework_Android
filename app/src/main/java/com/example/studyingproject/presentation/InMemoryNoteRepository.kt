package com.example.studyingproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.studyingproject.data.Note

class InMemoryNoteRepository: NoteRepository {
    private val notes = MutableLiveData<List<Note>>(emptyList())

    override fun addNote(note: Note) {
        val updatedList = notes.value.orEmpty().toMutableList().apply { add(note) }
        notes.value = updatedList
    }

    override fun getNotes(): LiveData<List<Note>> = notes

    override fun updateNote(note: Note) {
        note.isPurchased = note.isPurchased == false
    }
}