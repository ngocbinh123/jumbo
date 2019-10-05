package com.nnbinh.jumbo

import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.nnbinh.jumbo.di.DaggerAppComponent
import com.tumblr.remember.Remember
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApp: DaggerApplication() {
    lateinit var appComponent: DaggerAppComponent
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().create(this) as DaggerAppComponent
        return appComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        Remember.init(applicationContext, "com.nnbinh.jumbo")
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }
}