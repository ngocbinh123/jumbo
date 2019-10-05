package com.nnbinh.jumbo.helpers

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogHelper @Inject constructor() {
  fun v(message: String) {
    Log.v("VITS-Loger", message)
  }

  fun e(e: Throwable?) {
    Log.e("VITS-Loger", e?.localizedMessage ?: "Exception")
    e?.printStackTrace()
  }
}