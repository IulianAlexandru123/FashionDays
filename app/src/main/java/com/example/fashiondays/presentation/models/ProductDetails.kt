package com.example.fashiondays.presentation.models

data class ProductDetails(

    val productBrand: String,
    val productId: Int,
    val thumb: List<String>,
    val zoom: List<String>,
    val productName: String
)