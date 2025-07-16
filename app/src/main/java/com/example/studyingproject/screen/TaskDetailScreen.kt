package com.example.studyingproject.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studyingproject.TaskViewModel

@Composable
fun TaskDetailScreen(navController: NavController, taskViewModel: TaskViewModel, taskId: Int?) {
    val task = taskViewModel.tasks.find { it.id == taskId }
    var title by remember { mutableStateOf(task?.title ?: "") }

    Column(modifier = Modifier.padding(16.dp).padding(top = 32.dp)) {
        Text(
            text = "ID Задачи: ${taskId ?: "N/A"}",
            fontSize = 18.sp
        )
        Text(
            text = "Название: ",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )
        BasicTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(onClick = {
            task?.let {
                taskViewModel.updateTask(it.id, title)
                navController.popBackStack()
            }
        }) {
            Text("Сохранить")
        }

        Button(onClick = {
            task?.let {
                taskViewModel.removeTask(it.id)
                navController.popBackStack()
            }
        }) {
            Text("Удалить")
        }
    }
}