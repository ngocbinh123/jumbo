package com.nnbinh.jumbo.helpers

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.nnbinh.jumbo.R
import java.util.Date
import java.text.SimpleDateFormat


operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(new: T) {
  val value = this.value ?: arrayListOf()
  value.add(new)
  this.value = value
}

fun Snackbar.withColor(@ColorInt backgroundColor: Int, @ColorInt textColor: Int): Snackbar {
  this.view.setBackgroundColor(backgroundColor)
  this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(
      textColor)
  return this
}

fun Date.prettyTime(): String {
  return SimpleDateFormat("HH:mm dd-MM-yyyy").format(this)
}

@BindingAdapter("imgResId")
fun loadDamagePhoto(view: ImageView, resId: Int) {
//  view.setImageResource(resId)
  Glide.with(view).load(resId)
      .centerCrop()
      .placeholder(R.drawable.bg_login)
      .thumbnail(0.5f)
      .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
      .into(view)
}
