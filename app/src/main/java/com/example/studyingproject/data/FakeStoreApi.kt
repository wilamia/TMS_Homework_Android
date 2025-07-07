package com.example.studyingproject.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductDto


    @PUT("carts/{id}")
    suspend fun updateCart(@Path("id") id: Int, @Body cart: CartRequest): CartResponseDto


    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body product: ProductDto): ProductDto

    @POST("users")
    suspend fun addUser(@Body user: UserDto): Response<UserDto>

    @GET("carts/{userId}")
    suspend fun getCartItems(@Path("userId") userId: Int): CartResponseDto
}
