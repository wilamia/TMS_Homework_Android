package com.example.studyingproject.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.studyingproject.data.Pokemon

class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.url == newItem.url
    }
}
