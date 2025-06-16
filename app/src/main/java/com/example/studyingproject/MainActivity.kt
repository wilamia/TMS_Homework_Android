package com.example.studyingproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.studyingproject.user.UserRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var phone: Phone
    @Inject lateinit var userRepository: UserRepository
    lateinit var component: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

       component = DaggerAppComponent.create()
        component.inject(this)

        getInfo()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, FirstFragment())
            .commit()
    }

    private fun getInfo() {
        phone.checkState()
        userRepository.auth()
    }
}