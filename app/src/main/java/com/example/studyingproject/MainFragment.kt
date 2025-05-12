package com.example.studyingproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyingproject.adapter.RecyclerViewAdapter
import com.example.studyingproject.data.Note
import com.example.studyingproject.data.Status
import com.example.studyingproject.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/*Задачи
Задача 1: Экран "Асинхронный запрос":
Экран "Асинхронный запрос":
- Кнопка "Загрузить данные"
- ProgressBar (показывается во время загрузки, скрывается после)
- TextView для результата или ошибки
- Используйте suspend-функцию с delay (и случайно выбрасывайте Exception для имитации ошибки)
- Всё реализуйте через корутины, переключайте контексты (IO/Main)
- Отмена загрузки при закрытии экрана (ViewModel + viewModelScope)
- Показывайте результат или ошибку
Бонус:Добавьте возможность запускать несколько таких "загрузок" параллельно, выводите их статусы в RecyclerView.*/
class MainFragment : Fragment() {
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var viewBinding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf(
            Status(id = -1, "Checked")
        )

        adapter = RecyclerViewAdapter()
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.recyclerView.adapter = adapter

        viewBinding.button.setOnClickListener {
            viewModel.loadNotesParallel(list)
            viewBinding.progressBar.visibility = View.VISIBLE
            viewBinding.exceptionText.visibility = View.INVISIBLE
        }

        viewModel.data.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }

        viewModel.status.observe(viewLifecycleOwner) { statusMessage ->
            viewBinding.progressBar.visibility = View.INVISIBLE
            viewBinding.exceptionText.visibility = View.VISIBLE
            viewBinding.exceptionText.text = statusMessage.status
        }
    }

}
