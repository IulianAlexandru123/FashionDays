package com.example.fashiondays.presentation.ui.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fashiondays.databinding.ProductItemBinding
import com.example.fashiondays.presentation.models.ProductDetails

class ProductsAdapter :
    RecyclerView.Adapter<ProductsAdapter.ItemProductViewHolder>() {

    private var productsList: List<ProductDetails> = listOf()

    var callBack: ((Event) -> Unit)? = null

    fun setItems(products: List<ProductDetails>) {
        this.productsList = products
        notifyDataSetChanged()
    }


    inner class ItemProductViewHolder(binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val productName = binding.productNameTextView
        val brandName = binding.productBrandTextView
        val productImage = binding.productImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return ItemProductViewHolder(
            ProductItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = productsList.size

    override fun onBindViewHolder(holder: ItemProductViewHolder, position: Int) {
        val product = productsList[position]

        holder.productName.text = product.productName
        holder.brandName.text = product.productBrand
        Glide.with(holder.itemView.context).load(product.thumb.first())
            .into(holder.productImage)

        holder.itemView.setOnLongClickListener {
            callBack?.invoke(Event(product.productId, product.productName))
            true
        }
    }

    data class Event(val productId: Int, val productName: String)
}