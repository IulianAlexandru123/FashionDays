package com.example.fashiondays.data.dtos

import com.google.gson.annotations.SerializedName

data class ProductsListDTO(
    @SerializedName("products")
    val products: List<ProductDTO>
)