package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Person
import com.example.myapplication.databinding.StudentLayoutBinding


class RecyclerViewAdapter(private var items: List<Person>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.onBind(items[position], position) { position ->
            items[position].rating += 0.01
            notifyItemChanged(position)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            StudentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(val binding: StudentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Person, position: Int, onClick: (Int) -> Unit) {
            binding.nameTextView.text = item.name
            binding.ratingTextView.setOnClickListener {
                onClick.invoke(position)
            }
            binding.ratingTextView.text = item.rating.toString()

        }
    }

    fun updateView(list: List<Person>) {
        val diffUtilResult = DiffUtil.calculateDiff(DiffUtilCallback(list, oldList = items))
        items = list.toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
    }

}