package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyingproject.adapter.RecyclerViewAdapter
import com.example.studyingproject.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

/*Задачи
Задача 1: Экран ”Поиска":
Реализовать экран поиска по списку элементов с использованием Flow для обработки изменений текста,
1. На экране должны быть:
- EditText для ввода поискового запроса.
- ProgressBar для отображения загрузки.
- RecyclerView для отображения результатов поиска.
- TextView для отображения ошибок или "нет результатов".
2. Поиск должен срабатывать только если пользователь прекратил ввод на 300 мс и если введенный текст изменился*
3. Для каждого поискового запроса запускается имитация сетевого поиска (например, задержка через delay(1000)),
после чего возвращается результат — список строк, содержащих поисковый запрос.
4. Если пользователь вводит новый текст до завершения предыдущего поиска, старый поиск отменяется и начинается новый.
5. Если результат пуст — вывести "Ничего не найдено".
6. Если произошла ошибка (например, искусственно выбрасываемое исключение при определённом запросе) — отобразить сообщение об ошибке./
 */
class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

        viewBinding.editTextView.addTextChangedListener { text ->
            viewModel.queryFlow.value = text.toString()
        }

        collectFlow()
    }

    private fun initRecycler() {
        adapter = RecyclerViewAdapter()
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.recyclerView.adapter = adapter
    }
    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResults.collect { results ->
                if (results.isEmpty()) {
                    viewBinding.textView.text = "Ничего не найдено"
                    viewBinding.textView.visibility = View.VISIBLE
                } else {
                    viewBinding.textView.visibility = View.INVISIBLE
                }
                adapter.submitList(results)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                viewBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
                if (isLoading) {
                    viewBinding.textView.visibility = View.INVISIBLE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorMessage.collect { errorMessage ->
                errorMessage?.let {
                    viewBinding.textView.text = it
                    viewBinding.textView.visibility = View.VISIBLE
                }
            }
        }
    }
}


