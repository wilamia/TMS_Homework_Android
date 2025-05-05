package com.example.studyingproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
/*Реализуйте экран, отображающий список заметок и дату их добавления.
Хранение заметок реализовать в файле или в переменной.
Над списком должно быть текстовое поле и кнопка “Добавить”.
При сохранении заметка должна сохраняться в хранилище и отображаться в списке.
Используйте правила Clean Architecture, MVVM + LiveData*/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}