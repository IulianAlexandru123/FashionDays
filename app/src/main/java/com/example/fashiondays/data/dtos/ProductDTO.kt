package com.example.fashiondays.data.dtos

import com.example.fashiondays.presentation.models.ProductDetails


data class ProductDTO(
    val classification: String,
    val classification_id: String,
    val classification_name: String,
    val discount_type: String,
    val has_vrs: Int,
    val hv: Int,
    val product_brand: String,
    val product_brand_id: Int,
    val product_discount: Int,
    val product_id: Int,
    val product_images: ProductImages,
    val product_is_new: Int,
    val product_name: String,
    val product_original_price: Double,
    val product_price: Double,
    val product_stock_state: String,
    val product_tag_id: Int,
    val product_type: String,
    val screen_title: String,
    val sku: String,
    val stamps: List<Stamp>,
    val sub_classification: String,
    val sub_classification_id: String,
    val tag_name: String
)

fun ProductDTO.toProductDetails() : ProductDetails {
    return ProductDetails(
        this.product_brand,
        this.product_id,
        this.product_images.thumb,
        this.product_images.zoom,
        this.product_name
    )

}