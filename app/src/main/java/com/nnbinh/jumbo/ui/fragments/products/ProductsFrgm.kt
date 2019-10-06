package com.nnbinh.jumbo.ui.fragments.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.nnbinh.jumbo.BaseFragment
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.databinding.FrgmProductsBinding
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

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val binding = DataBindingUtil.inflate<FrgmProductsBinding>(
        inflater, R.layout.frgm_products, container, false)
    binding.lifecycleOwner = this

    return binding.root
  }
}