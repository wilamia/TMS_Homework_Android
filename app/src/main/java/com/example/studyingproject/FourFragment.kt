package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import com.example.studyingproject.adapter.RecyclerViewAdapter
import com.example.studyingproject.databinding.FragmentFourBinding
import com.example.studyingproject.databinding.FragmentNewBinding

class FourFragment : Fragment() {
    private var viewBinding: FragmentFourBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFourBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initList()
        initRecycler()
    }

    private fun initRecycler() {
        val list = listOf("dsf", "sdfa", "asdfs", "sfgd", "sadfs")
        val adapter = RecyclerViewAdapter(list)
        viewBinding?.recyclerView?.adapter = adapter
    }

    /*Использовать simple_list_item_2 в ListView
   Отображать заголовок и подзаголовок в каждой строке(использовать SimpleAdapter)*/
    private fun initList() {
//        val data: List<Map<String, String>> = List(50) {
//            mapOf("title" to "Title ${it + 1}", "sub-title" to "sub-title ${it + 1}")
//        }
//
//        val adapter = SimpleAdapter(
//            requireContext(),
//            data,
//            android.R.layout.simple_list_item_2,
//            arrayOf("title", "sub-title"),
//            intArrayOf(android.R.id.text1, android.R.id.text2)
//        )
//
//        viewBinding?.listView?.adapter = adapter
    }
}