package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.RecyclerViewAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val list = listOf(
            Person(id = 0, name = "dfs", rating = 5.0),
            Person(id = 1, name = "q", rating = 5.0),
            Person(id = 2, name = "f", rating = 5.0),
            Person(id = 3, name = "a", rating = 5.0),
            Person(id = 4, name = "k", rating = 5.0),
        )

        var copyList = list.toMutableList()
        val adapter = RecyclerViewAdapter(list)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.adapter = adapter
        binding?.addSymbol?.setOnClickListener {

            copyList.replaceAll {

                val new = it.name + "2"
                it.copy(new)
            }

            val newItems = copyList
            adapter.updateView(newItems)
        }
    }


}