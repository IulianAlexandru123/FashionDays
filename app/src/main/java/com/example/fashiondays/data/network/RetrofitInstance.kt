package com.example.fashiondays.data.network

import com.example.fashiondays.data.repository.ProductsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ProductsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://m.fashiondays.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }
}