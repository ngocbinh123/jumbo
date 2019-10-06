package com.nnbinh.jumbo.ui.cells

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nnbinh.jumbo.databinding.CellProductBinding
import com.nnbinh.jumbo.db.Product

class ProductCell(private val binding: CellProductBinding) : RecyclerView.ViewHolder(binding.root) {

  fun bind(product: Product) {
    binding.item = product
    binding.clickListener = onClickCell(product)
    binding.apply {
      executePendingBindings()
    }
  }

  fun onClickCell(product: Product): View.OnClickListener {
    return View.OnClickListener {
    }
  }
}