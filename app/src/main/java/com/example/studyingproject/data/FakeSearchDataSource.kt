package com.example.studyingproject.data

import com.example.studyingproject.domain.SearchRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FakeSearchDataSource : SearchRepository {

    private val data = listOf(
        "apple", "apricot", "banana", "blueberry", "blackberry",
        "cherry", "date", "fig", "grape", "grapefruit",
        "kiwi", "lemon", "lime", "mango", "melon",
        "nectarine", "orange", "peach", "pear", "pineapple",
        "plum", "raspberry", "strawberry", "watermelon"
    )

    override fun search(query: String): Observable<List<String>> {
        return Observable.fromCallable {
            Thread.sleep(300)
            if (query.equals("ошибка", ignoreCase = true)) {
                throw RuntimeException("Сервер вернул ошибку")
            }
            data
                .filter { it.contains(query, ignoreCase = true) }
        }.subscribeOn(Schedulers.io())
    }
}
