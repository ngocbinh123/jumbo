package com.nnbinh.jumbo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nnbinh.jumbo.ui.activities.login.LoginVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(LoginVM::class)
  internal abstract fun bindLoginVM(viewModel: LoginVM): ViewModel
}