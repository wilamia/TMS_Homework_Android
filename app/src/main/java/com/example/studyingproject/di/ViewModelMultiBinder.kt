package com.example.studyingproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyingproject.presentation.CartViewModel
import com.example.studyingproject.presentation.ProductDetailViewModel
import com.example.studyingproject.presentation.ProductListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelMultiBinder {

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel::class)
    abstract fun bindProductDetailViewModel(viewModel: ProductDetailViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    // Таким образом, internal обеспечивает видимость членов класса только для других частей того же модуля, в котором они определены, и скрывает их от других модулей.
    internal abstract fun bindProductListViewModel(viewModel: ProductListViewModel): ViewModel
    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}
