package com.example.studyingproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.classes.Elements
import com.example.studyingproject.classes.Elements.*
import com.example.studyingproject.databinding.ButtonLayoutBinding
import com.example.studyingproject.databinding.HeaderLayoutBinding
import com.example.studyingproject.databinding.TextLayoutBinding

class ElementsMultiTypeAdapter(private val data: List<Elements>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_TEXT = 1
        private const val TYPE_BUTTON = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is Header -> TYPE_HEADER
            is PostText -> TYPE_TEXT
            is Button -> TYPE_BUTTON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = HeaderLayoutBinding.inflate(layoutInflater, parent, false)
                HeaderViewHolder(binding)
            }

            TYPE_TEXT -> {
                val binding = TextLayoutBinding.inflate(layoutInflater, parent, false)
                TextViewHolder(binding)
            }

            TYPE_BUTTON -> {
                val binding = ButtonLayoutBinding.inflate(layoutInflater, parent, false)
                ButtonViewHolder(binding)
            }

            else -> {
                return throw IllegalStateException("")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(data[position] as Header)
            is TextViewHolder -> holder.bind(data[position] as PostText)
            is ButtonViewHolder -> holder.bind(data[position] as Button)
        }
    }

    override fun getItemCount(): Int = data.size

    class HeaderViewHolder(private val binding: HeaderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Header) {
            binding.headerTextView.text = item.title
        }
    }

    class TextViewHolder(private val binding: TextLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostText) {
            binding.textTextView.text = item.text
        }
    }

    class ButtonViewHolder(private val binding: ButtonLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Button) {
            binding.btn.text = item.name
        }
    }

}
