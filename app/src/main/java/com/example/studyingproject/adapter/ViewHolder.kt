package com.example.studyingproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.data.Note
import com.example.studyingproject.databinding.NoteViewBinding

class ViewHolder(private val binding: NoteViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(note: Note) {
        binding.noteText.text = note.text
        binding.noteTime.text = note.time.toString()
    }
}