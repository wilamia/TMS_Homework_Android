package com.example.studyingproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.databinding.NoteViewBinding
import com.example.studyingproject.data.Note

class ViewHolder(val binding: NoteViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(note: Note, onChecked: (Note) -> Unit) {
        binding.checkBox.text = note.text
        binding.checkBox.isChecked = note.isPurchased
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            onChecked(note.copy(isPurchased = isChecked))
        }
    }

}