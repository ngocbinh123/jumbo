package com.nnbinh.jumbo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nnbinh.jumbo.databinding.CellProductBinding
import com.nnbinh.jumbo.db.Product
import com.nnbinh.jumbo.ui.cells.ProductCell

class ProductsAdpt : ListAdapter<Product, ProductCell>(ProductDiffCallback()){
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCell {
    val binding = CellProductBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
    return ProductCell(binding)
  }

  override fun onBindViewHolder(holder: ProductCell, position: Int) {
    val product = getItem(position)
    holder.bind(product)
  }
}

private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
  override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
    return oldItem.name == newItem.name
  }

}