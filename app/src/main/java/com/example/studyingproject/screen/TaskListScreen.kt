package com.example.studyingproject.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studyingproject.data.Task
import com.example.studyingproject.TaskViewModel

@Composable
fun TaskListScreen(navController: NavController, taskViewModel: TaskViewModel) {
    val tasks = taskViewModel.tasks

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("newTask") }
            ) {
                Text("+")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        ) {
            if (tasks.isEmpty()) {
                item {
                    Text(
                        text = "Задач нет",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(tasks) { task ->
                    TaskItem(task) {
                        navController.navigate("taskDetail/${task.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Text(task.title, modifier = Modifier.padding(16.dp))
    }
}