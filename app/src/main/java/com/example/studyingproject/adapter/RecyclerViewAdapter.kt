package com.example.studyingproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.databinding.ItemNameBinding

class RecyclerViewAdapter(
    private val list: List<String>
): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "meow", Toast.LENGTH_SHORT).show()
        }
    }
}