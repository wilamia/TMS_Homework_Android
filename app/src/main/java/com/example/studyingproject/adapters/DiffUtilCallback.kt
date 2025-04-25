package com.example.studyingproject.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.studyingproject.classes.Posts

class DiffUtilCallback(val newData: List<Posts>, val oldData: List<Posts>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = newData[newItemPosition]
        return oldItem::class == newItem::class && oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }

}