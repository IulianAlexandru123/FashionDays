package com.example.fashiondays.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fashiondays.databinding.ProductsFragmentBinding
import com.example.fashiondays.presentation.ui.components.ProductsAdapter
import com.example.fashiondays.presentation.ProductsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {

    private var _binding: ProductsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var productsAdapter: ProductsAdapter
//    private val productsViewModel by lazy {
//        ViewModelProvider(this)[ProductsViewModel::class.java]
//    }

    private lateinit var productsViewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ProductsViewModel.Factory
        productsViewModel = ViewModelProvider(this, factory).get(ProductsViewModel::class.java)
        performInitialServerRequest()
        initViews()
        initObservers()
    }

    private fun performInitialServerRequest() {
        productsViewModel.getProducts()
    }

    private fun initViews() {
        binding.refreshLayout.setOnRefreshListener {
            performInitialServerRequest()
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                productsViewModel.productsList.collectLatest {
                    productsAdapter = ProductsAdapter()
                    productsAdapter.setItems(it)
                    productsAdapter.callBack = { event ->
                        productsViewModel.deleteProduct(event.productId)
                        Toast.makeText(
                            requireContext(),
                            "Product ${event.productName} deleted",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    binding.reservationsRecyclerView.apply {
                        adapter = productsAdapter
                        productsAdapter.setItems(it)
                    }
                }
            }
        }

        productsViewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.refreshLayout.isRefreshing = loading
        }
    }
}