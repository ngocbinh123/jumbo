package com.nnbinh.jumbo.di

import com.nnbinh.jumbo.ui.activities.login.LoginActivity
import com.nnbinh.jumbo.ui.activities.login.LoginModule
import com.nnbinh.jumbo.ui.activities.root.RootActivity
import com.nnbinh.jumbo.ui.activities.root.RootModule
import com.nnbinh.jumbo.ui.fragments.account.AccountFrgm
import com.nnbinh.jumbo.ui.fragments.account.AccountModule
import com.nnbinh.jumbo.ui.fragments.products.ProductsFrgm
import com.nnbinh.jumbo.ui.fragments.products.ProductsModule
import com.nnbinh.jumbo.ui.fragments.suppermarkets.SuperMarketsFrgm
import com.nnbinh.jumbo.ui.fragments.suppermarkets.SuperMarketsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuilderModule {
  @ScopeActivity
  @ContributesAndroidInjector(modules = [LoginModule::class])
  internal abstract fun bindLoginActivity(): LoginActivity

  @ScopeActivity
  @ContributesAndroidInjector(modules = [RootModule::class])
  internal abstract fun bindRootActivity(): RootActivity

  @ScopeFragment
  @ContributesAndroidInjector(modules = [SuperMarketsModule::class])
  internal abstract fun bindSupperMarketsFrgm(): SuperMarketsFrgm

  @ScopeFragment
  @ContributesAndroidInjector(modules = [ProductsModule::class])
  internal abstract fun bindProductsFrgm(): ProductsFrgm

  @ScopeFragment
  @ContributesAndroidInjector(modules = [AccountModule::class])
  internal abstract fun bindAccountFrgm(): AccountFrgm
}