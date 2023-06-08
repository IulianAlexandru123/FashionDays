package com.example.fashiondays.di

import android.app.Application
import com.example.fashiondays.domain.ProductsInteractorImpl
import com.example.fashiondays.domain.usecase.DeleteProductUseCase
import com.example.fashiondays.domain.usecase.GetProductsUseCase
import com.example.fashiondays.presentation.ProductsViewModel

class ProductsDependenciesContainer(application: Application) {

    private val getProductsUseCase by lazy {
        GetProductsUseCase()
    }

    private val deleteProductUseCase by lazy {
        DeleteProductUseCase()
    }

    private val productsInteractor by lazy {
        ProductsInteractorImpl(getProductsUseCase, deleteProductUseCase)
    }

    val productsViewModel: ProductsViewModel by lazy {
        ProductsViewModel(application = application, productsInteractorImpl = productsInteractor)
    }

    companion object {
        @JvmStatic
        fun get(application: Application): ProductsDependenciesContainer =
            ProductsDependenciesContainer(application = application)
    }
}