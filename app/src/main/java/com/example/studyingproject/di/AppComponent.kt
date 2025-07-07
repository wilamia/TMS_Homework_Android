package com.example.studyingproject.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.studyingproject.MainActivity
import com.example.studyingproject.data.FakeStoreApi
import com.example.studyingproject.presentation.CartFragment
import com.example.studyingproject.presentation.ProductDetailFragment
import com.example.studyingproject.presentation.ProductListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.MapKey
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
@Component(modules = [AppModule::class, ViewModelMultiBinder::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: ProductListFragment)
    fun inject(fragment: CartFragment)
    fun inject(fragment: ProductDetailFragment)
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}

@Qualifier
@Retention
annotation class FirstApi

@Qualifier
@Retention
annotation class SecondApi

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)