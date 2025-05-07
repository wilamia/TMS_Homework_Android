package com.example.studyingproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyingproject.databinding.FragmentMainBinding
import androidx.fragment.app.viewModels
import com.example.studyingproject.viewmodel.MainFragmentViewModel
import com.example.studyingproject.adapter.RecyclerViewAdapter
import com.example.studyingproject.data.Note

/*Задачи
Задача 1: Clean Architecture + SOLID + DRY + YAGNI + KISS
=Реализуйте простой экран "Список покупок".
Требования:
1. Пользователь видит список покупок (названия товаров).
2. Пользователь может добавить новый товар через поле ввода и кнопку "Добавить".
3. Пользователь может отметить товар как купленный (чекбокс).
4. Состояние списка сохраняется только в памяти
5. Экран реализуйте в одном Activity или Fragment.
6. Используйте RecyclerView для списка.*/
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecyclerViewAdapter
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        setView()
    }

    private fun setRecycler() {
        adapter = RecyclerViewAdapter { note -> viewModel.updateNote(note)}
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes.toList())
        }
    }

    private fun setView() {
        binding.button.setOnClickListener {
            val text = binding.editText.text.toString()
            if (text.isNotBlank()) {
                val newNote = Note(text)
                viewModel.addNote(newNote)
                binding.editText.setText("")
            }
        }
    }
}