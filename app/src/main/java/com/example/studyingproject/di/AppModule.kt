package com.example.studyingproject.di

import android.content.Context
import com.example.studyingproject.data.FakeStoreApi
import com.example.studyingproject.data.Product
import com.example.studyingproject.domain.CartRepository
import com.example.studyingproject.domain.CartRepositoryImpl
import com.example.studyingproject.domain.ProductDetailUseCase
import com.example.studyingproject.domain.ProductRepository
import com.example.studyingproject.domain.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @FirstApi
    fun provideFakeStoreApi(): FakeStoreApi = buildRetrofit().create(FakeStoreApi::class.java)

    @Provides
    @SecondApi
    fun provideFakeStoreApiSecond(): FakeStoreApi = buildRetrofit().create(FakeStoreApi::class.java)

    @Provides
    fun provideProductRepository(@SecondApi api: FakeStoreApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }

    @Provides
    fun provideCartRepository(@FirstApi api: FakeStoreApi, context: Context): CartRepository {
        return CartRepositoryImpl(api, context)
    }

    @Provides
    fun provideProductDetailUseCase(repository: ProductRepository): ProductDetailUseCase {
        return ProductDetailUseCase(repository)
    }
}