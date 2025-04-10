package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyingproject.adapter.RecyclerViewAdapter
import com.example.studyingproject.databinding.FragmentDynamicRecyclerViewBinding

/*Задача 1: Динамическое добавление элементов в RecyclerView
При нажатии на кнопку добавлять новый элемент в список.*/
/*Задача 2: Пустой экран в RecyclerView, если нет данных
Показать заглушку (например, TextView "Нет данных") вместо списка, если список пуст.*/

class DynamicRecyclerView : Fragment() {
    private var viewBinding: FragmentDynamicRecyclerViewBinding? = null
    private var list: MutableList<String> = mutableListOf()
    private val adapter = RecyclerViewAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDynamicRecyclerViewBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecycler()
    }

    private fun initView() {
        viewBinding?.button?.setOnClickListener {
            viewBinding?.textView3?.visibility = View.INVISIBLE
            list.add(viewBinding?.editText?.text.toString())
            adapter.notifyItemInserted(list.size-1)
            viewBinding?.editText?.text?.clear()
        }

        viewBinding?.button2?.setOnClickListener {
            list.clear()
            adapter.notifyDataSetChanged()
            viewBinding?.textView3?.visibility = View.VISIBLE
        }
    }

    private fun initRecycler() {
        viewBinding?.recyclerViewDynamic?.adapter = adapter
    }
}