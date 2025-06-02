package com.example.studyingproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.data.Product
import com.example.studyingproject.databinding.ItemCartBinding

class CartAdapter(
    private val onRemoveClick: (Int) -> Unit
) : ListAdapter<Product, CartAdapter.CartViewHolder>(DiffCallback()) {

    inner class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            binding.title.text = product.title
            binding.price.text = "${product.price} $"
            binding.removeButton.setOnClickListener {
                onRemoveClick(product.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}