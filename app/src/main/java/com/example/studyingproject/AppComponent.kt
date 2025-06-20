package com.example.studyingproject

import android.content.Context
import com.example.studyingproject.user.UserModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Qualifier


@Component(modules = [AppModule::class, UserModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            @BindsInstance @Email email: String,
            @BindsInstance @Name name: String
        ): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: FirstFragment)
}

@Qualifier
@Retention
annotation class Name

@Qualifier
@Retention
annotation class Email