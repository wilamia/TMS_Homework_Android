package com.example.studyingproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.data.Note
import com.example.studyingproject.data.Status
import com.example.studyingproject.databinding.NoteViewBinding

class ViewHolder(private val binding: NoteViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(status: Status) {
        binding.textView.text = status.status
    }
}