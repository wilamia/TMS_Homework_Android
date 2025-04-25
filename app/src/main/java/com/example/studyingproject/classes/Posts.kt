package com.example.studyingproject.classes

import android.graphics.drawable.Drawable

/*Имя Автора и Текст под ним
Картинка и текст под ней
Текст и кнопка под ней*/
sealed class Posts {
    data class Author(val author: String, val text: String) : Posts()
    data class Image(val image: Drawable?, val text: String) : Posts()
    data class Text(val text: String, val btnName: String) : Posts()
}