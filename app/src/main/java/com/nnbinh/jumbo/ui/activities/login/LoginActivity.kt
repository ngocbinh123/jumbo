package com.nnbinh.jumbo.ui.activities.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.nnbinh.jumbo.BaseActivity
import com.nnbinh.jumbo.BuildConfig
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.databinding.ActivityLoginBinding
import com.nnbinh.jumbo.obj.CurrentUser
import com.nnbinh.jumbo.ui.activities.root.RootActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.btn_login
import kotlinx.android.synthetic.main.activity_login.lout_password
import kotlinx.android.synthetic.main.activity_login.lout_user_name

class LoginActivity : BaseActivity() {
  override fun initViewModel(): ViewModel {
    return ViewModelProviders.of(this, factory)[LoginVM::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    binding.viewmodel = viewmodel as LoginVM
    binding.lifecycleOwner = this

    if (BuildConfig.DEBUG) {
      lout_user_name.editText?.setText("hnguyen@abiz.co")
      lout_password.editText?.setText( "Aa@1234567")
    }
    setupObservables()
    setupListeners()
  }

  private fun setupObservables() {
    (viewmodel as LoginVM).command.observe(this, Observer { command ->
      processCommand(command)
    })

    (viewmodel as LoginVM).isProcessing.observe(this, Observer { isProcess ->
      if (!isProcess && CurrentUser.isSignedIn()) {
        RootActivity.start(this)
        finish()
      }
    })
  }

  private fun setupListeners() {
    btn_login.setOnClickListener {
      hideKeyboard()
      val email = lout_user_name.editText?.text?.trim().toString()
      val password = lout_password.editText?.text?.trim().toString()
      (viewmodel as LoginVM).validateAndLogin(email = email, password = password)
    }
  }
}