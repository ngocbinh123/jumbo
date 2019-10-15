package com.nnbinh.jumbo.ui.fragments.suppermarkets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nnbinh.jumbo.BaseFragment
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.databinding.FrgmSupermarketsBinding
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.ui.adapters.SuperMarketsAdpt
import com.nnbinh.jumbo.ui.dialogs.CheckInOutDlg
import kotlinx.android.synthetic.main.frgm_supermarkets.rclv_supermarket
import kotlin.LazyThreadSafetyMode.SYNCHRONIZED

class SuperMarketsFrgm: BaseFragment() {
  companion object {
    fun getInstance(): SuperMarketsFrgm {
      return with(SuperMarketsFrgm()) {
        this
      }
    }
  }

  private val frgmVM by lazy(SYNCHRONIZED) {
    ViewModelProviders.of(this, factory)[SupperMarketsVM::class.java]
  }

  private val supermarketsAdpt by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    SuperMarketsAdpt()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val binding = DataBindingUtil.inflate<FrgmSupermarketsBinding>(
        inflater, R.layout.frgm_supermarkets, container, false)
    binding.lifecycleOwner = this
    setupObservers()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onDestroyView() {
    rclv_supermarket.adapter = null
    super.onDestroyView()
  }
  fun onSelectedSuperMarket(superMarket: SuperMarket) {
    CheckInOutDlg.getInstance(superMarket).show(childFragmentManager, CheckInOutDlg::class.java.name)
  }

  fun onSubmitWorkingTime() {
    frgmVM.onSubmitWorkingTime()
  }

  private fun setupObservers() {
    frgmVM.command.observe(this, Observer { processCommand(it) })

    frgmVM.supermarkets.observe(this, Observer { ls ->
      supermarketsAdpt.submitList(ls)
      rclv_supermarket.adapter = supermarketsAdpt
    })
  }
}