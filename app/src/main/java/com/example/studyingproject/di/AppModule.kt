package com.example.studyingproject.di

import com.example.studyingproject.data.FakeStoreApi
import com.example.studyingproject.data.Product
import com.example.studyingproject.domain.CartRepository
import com.example.studyingproject.domain.CartRepositoryImpl
import com.example.studyingproject.domain.ProductDetailUseCase
import com.example.studyingproject.domain.ProductRepository
import com.example.studyingproject.domain.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFakeStoreApi(): FakeStoreApi {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeStoreApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: FakeStoreApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCartRepository(api: FakeStoreApi): CartRepository {
        return CartRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProductDetailUseCase(repository: ProductRepository): ProductDetailUseCase {
        return ProductDetailUseCase(repository)
    }
}