package com.example.studyingproject.classes

sealed class Elements {
    data class Header(val title: String) : Elements()
    data class PostText(val text: String) : Elements()
    data class Button(val name: String) : Elements()
}
