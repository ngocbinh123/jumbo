package com.nnbinh.jumbo.helpers

import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar

operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(new: T) {
  val value = this.value ?: arrayListOf()
  value.add(new)
  this.value = value
}

fun Snackbar.withColor(@ColorInt backgroundColor: Int, @ColorInt textColor: Int): Snackbar {
  this.view.setBackgroundColor(backgroundColor)
  this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(textColor)
  return this
}