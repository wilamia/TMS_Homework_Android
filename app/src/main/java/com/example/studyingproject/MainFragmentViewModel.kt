package com.example.studyingproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyingproject.data.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

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

class MainFragmentViewModel : ViewModel() {
    private val _data: MutableLiveData<MutableList<Status>> = MutableLiveData(mutableListOf())
    val data: LiveData<MutableList<Status>> get() = _data

    private val _status: MutableLiveData<Status> = MutableLiveData()
    val status: LiveData<Status> get() = _status

    fun loadNotesParallel(statuses: List<Status>) {
        _status.value = Status(-1, "Загрузка...")

        val results = mutableListOf<Pair<Status, Boolean>>()

        statuses.forEachIndexed { index, status ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    randomException()
                    withContext(Dispatchers.Main) {
                        addNote(Status(index, "Загружено: ${status.status}"))
                        results.add(status to true)
                        checkFinalStatus(statuses.size, results)
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        addNote(Status(index, "Ошибка при загрузке: ${status.status}"))
                        results.add(status to false)
                        checkFinalStatus(statuses.size, results, e.message)
                    }
                }
            }
        }
    }

    private suspend fun randomException() {
        delay(1000L + Random.nextLong(1000))
        if (Random.nextBoolean()) throw Exception("Ошибка при загрузке: ${status.value}")
    }

    private fun checkFinalStatus(total: Int, results: List<Pair<Status, Boolean>>, errorMsg: String? = null) {
        if (results.size < total) return

        val failureCount = results.count { !it.second }

        _status.value = if (failureCount == 0) {
            Status(-1, "Все успешно загружено")
        } else {
            Status(-1, "Не удалось загрузить все элементы")
        }
    }

    private fun addNote(status: Status) {
        val current = _data.value ?: mutableListOf()
        _data.value = current.toMutableList().apply { add(status) }
    }
}


