package com.example.studyingproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.studyingproject.databinding.NoteViewBinding
import com.example.studyingproject.data.Note

class RecyclerViewAdapter(private val onNoteChecked: (Note) -> Unit) :
    ListAdapter<Note, ViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item, onNoteChecked)
    }

}
