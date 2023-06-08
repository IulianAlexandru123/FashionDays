package com.example.fashiondays.data.repository

import com.example.fashiondays.data.dtos.ProductsListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductsApi {

    @GET("/mobile/8/g/women/clothing")
    suspend fun getProducts(@Header("x-mobile-app-locale") value: String): Response<ProductsListDTO>
}