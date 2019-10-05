package com.nnbinh.jumbo.di

import com.nnbinh.jumbo.ui.activities.login.LoginActivity
import com.nnbinh.jumbo.ui.activities.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuilderModule {
  @ScopeActivity
  @ContributesAndroidInjector(modules = [LoginModule::class])
  internal abstract fun bindLoginActivity(): LoginActivity

//  @ScopeFragment
//  @ContributesAndroidInjector(modules = [FBInModule::class])
//  internal abstract fun bindFBInFrgm(): FBInFrgm
}