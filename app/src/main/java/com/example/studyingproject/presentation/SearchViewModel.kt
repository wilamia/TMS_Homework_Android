package com.example.studyingproject.presentation

import android.util.Log
import com.example.studyingproject.domain.SearchUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SearchViewModel(private val useCase: SearchUseCase) {

    private val disposables = CompositeDisposable()

    fun bindSearchInput(input: Observable<CharSequence>, onResult: (List<String>) -> Unit) {
        val disposable = input
            .debounce(300, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .distinctUntilChanged()
            .switchMap { query ->
                useCase.execute(query)
                    .onErrorResumeNext { throwable: Throwable ->
                        Log.e("Search", "Error: ${throwable.message}")
                        return@onErrorResumeNext Observable.just(emptyList())
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onResult)

        disposables.add(disposable)
    }

    fun clear() {
        disposables.clear()
    }
}