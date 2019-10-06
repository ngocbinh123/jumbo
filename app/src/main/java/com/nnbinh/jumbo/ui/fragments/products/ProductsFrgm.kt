package com.nnbinh.jumbo.ui.fragments.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nnbinh.jumbo.BaseFragment
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.databinding.FrgmProductsBinding
import com.nnbinh.jumbo.ui.adapters.ProductsAdpt
import kotlinx.android.synthetic.main.frgm_products.rclv_products
import kotlin.LazyThreadSafetyMode.SYNCHRONIZED

class ProductsFrgm : BaseFragment() {
  companion object {
    fun getInstance(): ProductsFrgm {
      return with(ProductsFrgm()) {
        this
      }
    }
  }
  private val frgmVM by lazy(SYNCHRONIZED) {
    ViewModelProviders.of(this, factory)[ProductsVM::class.java]
  }

  private val productsAdpt by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    ProductsAdpt()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val binding = DataBindingUtil.inflate<FrgmProductsBinding>(
        inflater, R.layout.frgm_products, container, false)
    binding.lifecycleOwner = this
    setupObservers()
    return binding.root
  }

  private fun setupObservers() {
    frgmVM.command.observe(this, Observer { processCommand(it) })

    frgmVM.products.observe(this, Observer { ls ->
      productsAdpt.submitList(ls)
      rclv_products.adapter = productsAdpt
    })
  }
}