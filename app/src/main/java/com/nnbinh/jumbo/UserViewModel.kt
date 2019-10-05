package com.nnbinh.jumbo

import android.app.Application
import androidx.lifecycle.ViewModel
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.event.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class UserViewModel : ViewModel() {
  @Inject lateinit var app: Application

  val command: SingleLiveEvent<Command> = SingleLiveEvent() //has only 1 observer on Activity
  val rxDispose = CompositeDisposable()

  override fun onCleared() {
    rxDispose.clear()
    super.onCleared()
  }

  fun logout() {
  }
}