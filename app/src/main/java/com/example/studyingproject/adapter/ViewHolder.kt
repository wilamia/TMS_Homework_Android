package com.example.studyingproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.databinding.NoteViewBinding

class ViewHolder(private val binding: NoteViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(text: String) {
        binding.textView.text = text
    }
}