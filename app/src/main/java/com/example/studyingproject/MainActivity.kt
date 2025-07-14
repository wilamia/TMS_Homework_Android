package com.example.studyingproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textView = findViewById<TextView>(R.id.txt)
        val observable = Observable.range(1, 20)
            .filter { it % 2 == 0 }
            .map { it * it * it }
            .subscribe {
                println(it)
            }

        val array = listOf("Kate", "Nastya", "Anton", "Marya", "Mana", "Klara")
        val nameObservable = Observable.fromIterable(array)
            .flatMap {
                getMark(it).map { grade ->
                    "$it $grade"
                }.toObservable()
            }.subscribe {
                println(it)
            }

        val numbersObservable = Observable.range(1, 100)
            .skip(10)
            .take(10)
            .subscribe {
                println(it)
            }

        val symbolsObservable = Observable.just('a', 'b', 'b', 'b', 'f', 'a')
            .distinct()
            .subscribe {
                println(it)
            }

        var disposable: Disposable? = null
        findViewById<Button>(R.id.btn).setOnClickListener {
            disposable?.dispose()
            disposable = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    textView.text = it.toString()
                }
        }

        findViewById<EditText>(R.id.editText).doAfterTextChanged {
            val changeObservable = Observable.just(it.toString())
                .debounce(2000, TimeUnit.MILLISECONDS)
                .flatMap {
                    Observable.fromIterable(array.filter { name ->
                       name.contains(it, true)
                    }.toList())
                }
                .subscribe {
                    Log.d("MYTAG", "$it")
                }
        }
    }

    fun getMark(name: String): Maybe<Int> =
        when (name) {
            "Kate" -> Maybe.just(5)
            "Nastya" -> Maybe.just(8)
            "Anton" -> Maybe.just(6)
            else -> Maybe.empty()
        }
}