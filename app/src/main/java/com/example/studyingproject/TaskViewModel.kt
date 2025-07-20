package com.example.studyingproject

import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.studyingproject.data.Task
import kotlin.coroutines.coroutineContext

class TaskViewModel  : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> get() = _tasks

    fun addTask(title: String) {
        val newTask = Task(id = _tasks.size, title = title)
        _tasks.add(newTask)
    }

    fun removeTask(taskId: Int) {
        _tasks.removeIf { it.id == taskId }
    }

    fun updateTask(taskId: Int, title: String) {
        _tasks.find { it.id == taskId }?.let {
            _tasks[_tasks.indexOf(it)] = it.copy(title = title)
        }
    }
}