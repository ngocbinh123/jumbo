package com.nnbinh.jumbo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nnbinh.jumbo.ui.activities.login.LoginVM
import com.nnbinh.jumbo.ui.activities.root.RootVM
import com.nnbinh.jumbo.ui.fragments.account.AccountVM
import com.nnbinh.jumbo.ui.fragments.products.ProductsVM
import com.nnbinh.jumbo.ui.fragments.suppermarkets.SupperMarketsVM
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

  @Binds
  @IntoMap
  @ViewModelKey(RootVM::class)
  internal abstract fun bindRootVM(viewModel: RootVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(SupperMarketsVM::class)
  internal abstract fun bindSupperMarketsVM(viewModel: SupperMarketsVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(ProductsVM::class)
  internal abstract fun bindProductsVM(viewModel: ProductsVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(AccountVM::class)
  internal abstract fun bindAccountVM(viewModel: AccountVM): ViewModel
}