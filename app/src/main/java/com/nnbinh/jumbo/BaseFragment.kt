package com.nnbinh.jumbo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.helpers.LogHelper
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : DialogFragment() {
  @Inject lateinit var factory: ViewModelProvider.Factory
  @Inject lateinit var logHelper: LogHelper

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    logHelper.v("onCreate " + this::class.java)
  }

  override fun onDestroy() {
    super.onDestroy()
    logHelper.v("onDestroy " + this::class.java)
  }

  open fun processCommand(command: Command) {
    (activity as? BaseActivity)?.processCommand(command)
  }
}