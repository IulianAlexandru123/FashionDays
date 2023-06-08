package com.example.fashiondays.domain.repository

import com.example.fashiondays.presentation.models.ProductDetails

interface ProductsInteractor {

    fun getAllProducts(emitCallback: (List<ProductDetails>) -> Unit)
    fun deleteProduct(productId: Int, productList: List<ProductDetails>) : List<ProductDetails>
}