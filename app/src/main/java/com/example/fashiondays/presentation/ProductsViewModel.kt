package com.example.fashiondays.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.fashiondays.di.ProductsDependenciesContainer
import com.example.fashiondays.domain.ProductsInteractorImpl
import com.example.fashiondays.presentation.models.ProductDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductsViewModel(
    val application: Application,
    private val productsInteractorImpl: ProductsInteractorImpl
) : ViewModel() {

    private val _productsList = MutableStateFlow<List<ProductDetails>>(listOf())
    val productsList: SharedFlow<List<ProductDetails>> = _productsList.asStateFlow()

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getProducts() {
        productsInteractorImpl.getAllProducts {
            _isLoading.postValue(false)
            _productsList.value = it
        }
    }

    fun deleteProduct(productId: Int) {
        _productsList.value = productsInteractorImpl.deleteProduct(productId, _productsList.value)
    }

    companion object {
        val TAG = ProductsViewModel::class.java.simpleName
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val productsDependenciesContainer = ProductsDependenciesContainer.get(application)

                return productsDependenciesContainer.productsViewModel as T
            }
        }
    }
}