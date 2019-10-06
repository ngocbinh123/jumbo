package com.nnbinh.jumbo.ui.cells

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nnbinh.jumbo.databinding.CellSupermarketBinding
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.ui.activities.root.RootActivity

class SuperMarketCell(private val binding: CellSupermarketBinding) : RecyclerView.ViewHolder(
    binding.root) {

  fun bind(superMarket: SuperMarket) {
    binding.item = superMarket
    binding.clickListener = onClickCell(superMarket)
    binding.apply {
      executePendingBindings()
    }
  }

  fun onClickCell(superMarket: SuperMarket): View.OnClickListener {
    return View.OnClickListener {
      (it.context as? RootActivity)?.onClickSuperMarket(superMarket)
    }
  }
}