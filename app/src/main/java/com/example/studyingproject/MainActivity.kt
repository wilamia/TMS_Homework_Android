package com.example.studyingproject

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    private var text: TextView? = null
    private var wifiReceiver: WifiReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("TAG", "onCreate")
        text = findViewById(R.id.editTextText)

        /*Получите строку из ресурсов в Context.*/
        text?.text = getString(R.string.username)

        /*Создай BroadcastReceiver, который реагирует на включение Wi-Fi.*/
        wifiReceiver = WifiReceiver()

        /*Создай кнопку, при нажатии на которую открывается другая Activity.*/
        button = findViewById(R.id.newActivityBtn)
        button?.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            /*Передай имя пользователя из MainActivity в SecondActivity.*/
            intent.putExtra("USERNAME", text?.text.toString())
            startActivity(intent)
        }
    }

    /*Создай Activity, которая будет логировать все этапы жизненного цикла.*/
    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart")
        val intentFilter = IntentFilter("android.net.wifi.WIFI_STATE_CHANGED")
        registerReceiver(wifiReceiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop")
        unregisterReceiver(wifiReceiver)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy");
    }
}