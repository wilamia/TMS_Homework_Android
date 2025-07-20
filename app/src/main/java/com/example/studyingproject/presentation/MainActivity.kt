package com.example.studyingproject.presentation

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studyingproject.R
import com.example.studyingproject.data.FakeSearchDataSource
import com.example.studyingproject.domain.SearchUseCase
import com.jakewharton.rxbinding2.widget.RxTextView

class MainActivity : AppCompatActivity() {

    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)

        val repository = FakeSearchDataSource()
        val useCase = SearchUseCase(repository)
        viewModel = SearchViewModel(useCase)

        viewModel.bindSearchInput(
            RxTextView.textChanges(autoCompleteTextView)
        ) { results ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, results)
            autoCompleteTextView.setAdapter(adapter)
            autoCompleteTextView.showDropDown()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}