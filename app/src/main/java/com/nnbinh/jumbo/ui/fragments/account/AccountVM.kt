package com.nnbinh.jumbo.ui.fragments.account

import com.nnbinh.jumbo.MissionViewModel
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.event.SingleLiveEvent
import com.nnbinh.jumbo.obj.CurrentUser
import com.nnbinh.jumbo.repo.UserRepo
import com.tumblr.remember.Remember
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountVM @Inject constructor() : MissionViewModel() {
  @Inject lateinit var userRepo: UserRepo
  fun logout() {
    val dispose = Observable.fromCallable {
      userRepo.clearUser()
    }.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe {
          command.value = Command.LogoutSuccess()
        }
    rxDispose.add(dispose)
  }
}