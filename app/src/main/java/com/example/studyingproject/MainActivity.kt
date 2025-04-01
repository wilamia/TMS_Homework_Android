package com.example.studyingproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    var button: Button? = null
    var secondButton: Button? = null
    var textViewFromEdit: TextView? = null
    var editText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
/*Добавь строковый ресурс "Привет, %s!", чтобы передавать имя пользователя в TextView.*/
        val text = getString(R.string.user_welcoming, "Ксения")
        textView = findViewById(R.id.textViewUser)
        textView?.setText(text)
/*Добавь кнопку, которая при нажатии покажет Toast с текстом "Кнопка нажата!".*/
        button = findViewById(R.id.button)
        button?.setOnClickListener {
            Toast.makeText(this, "Кнопка нажата!", Toast.LENGTH_SHORT).show()
        }

        secondButton = findViewById(R.id.button2)
        textViewFromEdit = findViewById(R.id.textView)
        editText = findViewById(R.id.edit_text)
        secondButton?.setOnClickListener {
            textViewFromEdit?.setText(editText?.text)
            editText?.setText("")
        }
    }
}