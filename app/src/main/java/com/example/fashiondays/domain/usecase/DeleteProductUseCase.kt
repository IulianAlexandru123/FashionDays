package com.example.fashiondays.domain.usecase

import com.example.fashiondays.presentation.models.ProductDetails


class DeleteProductUseCase() {

    operator fun invoke(productId: Int, productList: List<ProductDetails>): List<ProductDetails> {
        return productList.toMutableList()
            .also { it.removeIf { elem -> elem.productId == productId } }
    }
}