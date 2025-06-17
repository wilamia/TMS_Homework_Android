package com.example.studyingproject.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.studyingproject.data.Product
import com.example.studyingproject.databinding.ItemProductBinding

class ProductViewHolder(private val binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(product: Product, onItemClick: ((Product) -> Unit)?) {
        binding.title.text = product.title
        binding.price.text = "$${product.price}"
        binding.image.load(product.image)
        binding.root.setOnClickListener {
            onItemClick?.invoke(product)
        }
    }
}
