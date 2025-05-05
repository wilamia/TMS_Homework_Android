package com.example.studyingproject
/*Реализуйте экран, отображающий список заметок и дату их добавления.
Хранение заметок реализовать в файле или в переменной.
Над списком должно быть текстовое поле и кнопка “Добавить”.
При сохранении заметка должна сохраняться в хранилище и отображаться в списке.
Используйте правила Clean Architecture, MVVM + LiveData*/

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyingproject.data.Note
import com.example.studyingproject.databinding.FragmentMainBinding
import androidx.fragment.app.viewModels
import com.example.studyingproject.adapter.RecyclerViewAdapter
import com.example.studyingproject.utils.DateUtils.getCurrentDateTimeFormatted

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setView()
    }

    private fun setRecyclerView() {
        adapter = RecyclerViewAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner) { noteList ->
            adapter.submitList(noteList.toList())
        }
    }

    private fun setView() {
        binding.createBtn.setOnClickListener {
            val text = binding.editTextTextMultiLine.text.toString()
            if (text.isNotBlank()) {
                val newNote = Note(text, getCurrentDateTimeFormatted())
                viewModel.addNote(newNote)
                binding.editTextTextMultiLine.setText("")
            }
        }
    }
}