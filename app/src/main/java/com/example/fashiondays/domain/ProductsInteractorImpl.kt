package com.example.fashiondays.domain

import com.example.fashiondays.domain.repository.ProductsInteractor
import com.example.fashiondays.domain.usecase.DeleteProductUseCase
import com.example.fashiondays.domain.usecase.GetProductsUseCase
import com.example.fashiondays.presentation.models.ProductDetails

class ProductsInteractorImpl(
    val getAllProductsUseCase: GetProductsUseCase,
    val deleteProductUseCase: DeleteProductUseCase
) : ProductsInteractor {
    override fun getAllProducts(emitCallback: (List<ProductDetails>) -> Unit) {
        getAllProductsUseCase.invoke(emitCallback)
    }

    override fun deleteProduct(productId: Int, productList: List<ProductDetails>) : List<ProductDetails> {
        return deleteProductUseCase.invoke(productId, productList)
    }
}