package com.nnbinh.jumbo

import android.app.Application
import android.os.Environment
import androidx.lifecycle.ViewModel
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.event.SingleLiveEvent
import com.nnbinh.jumbo.helpers.ErrorParserHelper
import com.nnbinh.jumbo.repo.UserRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class UserViewModel : ViewModel() {
  @Inject
  lateinit var app: Application
  @Inject
  lateinit var userRepo: UserRepo
  @Inject
  lateinit var errorHelper: ErrorParserHelper

  val command: SingleLiveEvent<Command> = SingleLiveEvent() //has only 1 observer on Activity
  val rxDispose = CompositeDisposable()

  override fun onCleared() {
    rxDispose.clear()
    super.onCleared()
  }

  fun signOut() {
    val dispose = Observable.fromCallable {
      userRepo.clearUser()
      val dir = app.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
      if (dir!!.exists()) {
        dir.listFiles().forEach { it.deleteRecursively() }
      }
    }.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(
            {
              command.value = Command.LogoutSuccess()
            },
            {
              command.value = errorHelper.parse(it)
            }
        )
    rxDispose.add(dispose)
  }
}