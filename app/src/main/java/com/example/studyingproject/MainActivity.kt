package com.example.studyingproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val myFragment = MyFragment()
        shareData(myFragment)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, myFragment)
            .commit()

        button = findViewById(R.id.button)
        moveToSecondActivity()
    }

    /*Открыть SecondActivity из MainActivity, передав строку "Hello, Second Activity!".*/
    private fun moveToSecondActivity() {
        button?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("MESSAGE", "Hello, Second Activity!")
            startActivity(intent)
        }
    }

    /*Передай строку "Data from Activity" из Activity в Fragment.*/
    private fun shareData(fragment: Fragment) {
        val string = "Data from Activity"

        val bundle = Bundle()
        bundle.putString("MESSAGE", string)
        fragment.arguments = bundle

    }

}
