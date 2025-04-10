package com.example.studyingproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.databinding.ItemNameBinding

class ViewHolder(private val binding: ItemNameBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: String) {
        binding.textView.text = item
    }
}
