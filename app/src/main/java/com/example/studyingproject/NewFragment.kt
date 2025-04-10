package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.studyingproject.databinding.FragmentFirstBinding
import com.example.studyingproject.databinding.FragmentNewBinding

class NewFragment : Fragment() {
  private var viewBinding: FragmentNewBinding? = null
    private var list = ArrayList<String>().apply{
        repeat(100) {
            add("meow $it")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }
/*Задача 1: Отобразить список строк с помощью ListView
Отобразить список из 100 строк ("Элемент 1", "Элемент 2", ...) в ListView.*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    initListView()
    }

    private fun initListView() {
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, list)
        viewBinding?.listView?.adapter = adapter
    }
}