package com.example.studyingproject.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.studyingproject.TaskViewModel
import com.example.studyingproject.screen.NewTaskScreen
import com.example.studyingproject.screen.TaskDetailScreen
import com.example.studyingproject.screen.TaskListScreen


@Composable
fun TaskApp() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()

    NavHost(navController, startDestination = "taskList") {
        composable("taskList") { TaskListScreen(navController, taskViewModel) }
        composable("taskDetail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            TaskDetailScreen(navController, taskViewModel, taskId)
        }
        composable("newTask") { NewTaskScreen(navController, taskViewModel) }
    }
}