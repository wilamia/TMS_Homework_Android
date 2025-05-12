package com.example.studyingproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.studyingproject.data.Note
import com.example.studyingproject.data.Status
import com.example.studyingproject.databinding.NoteViewBinding

class RecyclerViewAdapter : ListAdapter<Status, ViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Status>() {
    override fun areItemsTheSame(oldItem: Status, newItem: Status): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Status, newItem: Status): Boolean {
        return oldItem == newItem
    }
}