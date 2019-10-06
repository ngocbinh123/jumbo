package com.nnbinh.jumbo.ui.fragments.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nnbinh.jumbo.BaseFragment
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.databinding.FrgmAccountBinding
import kotlinx.android.synthetic.main.frgm_account.btn_sign_out
import kotlin.LazyThreadSafetyMode.SYNCHRONIZED

class AccountFrgm: BaseFragment() {
  companion object {
    fun getInstance(): AccountFrgm {
      return with(AccountFrgm()) {
        this
      }
    }
  }
  private val frgmVM by lazy(SYNCHRONIZED) {
    ViewModelProviders.of(this, factory)[AccountVM::class.java]
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val binding = DataBindingUtil.inflate<FrgmAccountBinding>(
        inflater, R.layout.frgm_account, container, false)
    binding.lifecycleOwner = this
    binding.frgmVM = frgmVM
    setupObservers()

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    btn_sign_out.setOnClickListener {
      frgmVM.logout()
    }
  }

  private fun setupObservers() {
    frgmVM.command.observe(this, Observer { processCommand(it) })
  }
}