package com.nnbinh.jumbo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nnbinh.jumbo.databinding.CellSupermarketBinding
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.ui.cells.SuperMarketCell

class SuperMarketsAdpt: ListAdapter<SuperMarket, SuperMarketCell>(SuperMarketDiffCallback()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperMarketCell {
    val binding = CellSupermarketBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
    return SuperMarketCell(binding = binding)
  }

  override fun onBindViewHolder(holder: SuperMarketCell, position: Int) {
    val item = getItem(position)
    holder.bind(item)
  }
}

private class SuperMarketDiffCallback : DiffUtil.ItemCallback<SuperMarket>() {

  override fun areItemsTheSame(oldItem: SuperMarket, newItem: SuperMarket): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: SuperMarket, newItem: SuperMarket): Boolean {
    return oldItem == newItem
  }
}