package com.example.studyingproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.studyingproject.data.Note

class NoteRepository {
    private val notes = MutableLiveData<MutableList<Note>>(mutableListOf())

    fun addNote(note: Note) {
        val currentList = notes.value ?: mutableListOf()
        currentList.add(note)
        notes.value = currentList
    }

    fun getNotes(): LiveData<MutableList<Note>> = notes
}