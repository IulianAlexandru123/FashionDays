package com.example.fashiondays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fashiondays.databinding.ActivityMainBinding
import com.example.fashiondays.presentation.ui.ProductsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme(R.style.Theme_FashionDays)

        val productsFragment = ProductsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, productsFragment).commit()
    }
}