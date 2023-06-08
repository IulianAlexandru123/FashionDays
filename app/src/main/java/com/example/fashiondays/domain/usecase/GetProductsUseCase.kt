package com.example.fashiondays.domain.usecase

import android.util.Log
import com.example.fashiondays.data.dtos.toProductDetails
import com.example.fashiondays.data.network.RetrofitInstance
import com.example.fashiondays.presentation.ProductsViewModel
import com.example.fashiondays.presentation.models.ProductDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GetProductsUseCase() {

    operator fun invoke(emitCallback: (List<ProductDetails>) -> Unit) {
        CoroutineScope(Dispatchers.IO + Job()).launch() {
            val response = try {
                RetrofitInstance.api.getProducts("ro_RO")
            } catch (e: IOException) {
                Log.e(ProductsViewModel.TAG, e.toString())
                return@launch
            } catch (e: HttpException) {
                Log.e(ProductsViewModel.TAG, e.toString())
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val products = response.body()?.products ?: emptyList()
                emitCallback(products.map { it.toProductDetails() })
                //_productsList.emit()
            } else {
                Log.e(ProductsViewModel.TAG, "Response not successful")
            }
        }
    }
}