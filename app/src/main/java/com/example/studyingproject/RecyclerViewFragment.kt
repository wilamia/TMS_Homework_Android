package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.studyingproject.adapter.RecyclerViewAdapter
import com.example.studyingproject.databinding.FragmentRecyclerViewBinding
import com.example.studyingproject.databinding.FragmentScrollViewBinding
/*Задача 5: Отображение RecyclerView в NestedScrollView
Отобразить блок с RecyclerView внутри прокручиваемого контента.*/
class RecyclerViewFragment : Fragment() {
    private var viewBinding: FragmentRecyclerViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val list =  ArrayList<String>().apply{
            repeat(100) {
                add("meow $it")
            }
        }
        val adapter = RecyclerViewAdapter(list)
        viewBinding?.recyclerView?.adapter = adapter
    }
}