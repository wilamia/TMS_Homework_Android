package com.example.studyingproject.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.studyingproject.data.Product

class DiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}