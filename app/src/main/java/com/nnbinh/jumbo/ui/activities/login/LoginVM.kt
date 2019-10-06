package com.nnbinh.jumbo.ui.activities.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.event.SingleLiveEvent
import com.nnbinh.jumbo.obj.Constant
import com.nnbinh.jumbo.obj.Constant.PASSWORD_MIN_LENGTH
import com.nnbinh.jumbo.obj.Constant.USERNAME_MAX_LENGTH
import com.nnbinh.jumbo.obj.Constant.USERNAME_MIN_LENGTH
import com.tumblr.remember.Remember
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.Calendar
import javax.inject.Inject

class LoginVM @Inject constructor() : ViewModel() {
  val command: SingleLiveEvent<Command> = SingleLiveEvent()
  val isProcessing = MutableLiveData(false)
  val rxDispose = CompositeDisposable()

  override fun onCleared() {
    rxDispose.clear()
    super.onCleared()
  }

  fun validateAndLogin(username: String, password: String) {
    // Validate
    if (username.length < USERNAME_MIN_LENGTH || username.length > USERNAME_MAX_LENGTH) {
      command.value = Command.Snack(resId = R.string.invalid_username_length)
      return
    }

    if (password.length < PASSWORD_MIN_LENGTH) {
      command.value = Command.Snack(resId = R.string.invalid_password_length)
      return
    }

    if (!password.matches("^[0-9]+$".toRegex())) {
      command.value = Command.Snack(resId = R.string.invalid_password_character)
      return
    }

    val dispose = Observable.fromCallable {
      Remember.putLong(Constant.PREF_KEY_USER_ID, 101L)
      Remember.putString(Constant.PREF_KEY_USER_NAME, username)
      Remember.putString(Constant.PREF_KEY_USER_TOKEN, "jumbo_${username}_${Calendar.getInstance().timeInMillis}")
    }.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnSubscribe { isProcessing.value = true }
        .doFinally { isProcessing.value = false }
        .subscribe {

        }
    rxDispose.add(dispose)
  }
}