package com.nnbinh.jumbo.di

import com.nnbinh.jumbo.BaseApp
import com.nnbinh.jumbo.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  AppModule::class,
  BuilderModule::class,
  ViewModelModule::class])
interface AppComponent : AndroidInjector<BaseApp> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<BaseApp>()
}