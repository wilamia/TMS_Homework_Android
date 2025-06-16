package com.example.studyingproject

import com.example.studyingproject.user.UserModule
import dagger.Component


@Component(modules = [AppModule::class, UserModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: FirstFragment)
}