package com.example.studyingproject.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studyingproject.TaskViewModel

@Composable
fun NewTaskScreen(navController: NavController, taskViewModel: TaskViewModel) {
    var taskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Новая задача",
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp, top = 32.dp)
        )
        BasicTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp, top = 8.dp)
        )

        Button(onClick = {
            taskViewModel.addTask(taskTitle)
            navController.popBackStack()
        }) {
            Text("Добавить задачу")
        }
    }
}