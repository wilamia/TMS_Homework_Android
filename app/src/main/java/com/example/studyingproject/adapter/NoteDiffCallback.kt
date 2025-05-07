package com.example.studyingproject.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.studyingproject.data.Note

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem.text == newItem.text
    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem
}