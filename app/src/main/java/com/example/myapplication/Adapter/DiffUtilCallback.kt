package com.example.myapplication.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.Person

class DiffUtilCallback(val list: List<Person>, val oldList: List<Person>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = list.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == list[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == list[newItemPosition]
    }
}