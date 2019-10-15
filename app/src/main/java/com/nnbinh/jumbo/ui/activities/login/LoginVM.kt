package com.nnbinh.jumbo.ui.activities.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.R.drawable
import com.nnbinh.jumbo.db.AccountInfo
import com.nnbinh.jumbo.db.Product
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.event.SingleLiveEvent
import com.nnbinh.jumbo.helpers.ErrorParserHelper
import com.nnbinh.jumbo.helpers.LogHelper
import com.nnbinh.jumbo.obj.RemoteUser
import com.nnbinh.jumbo.repo.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginVM @Inject constructor(val userRepo: UserRepo) : ViewModel() {
  @Inject lateinit var logHelper: LogHelper
  @Inject lateinit var errorHelper: ErrorParserHelper

  val command: SingleLiveEvent<Command> = SingleLiveEvent()
  val isProcessing = MutableLiveData(false)
  val rxDispose = CompositeDisposable()

  override fun onCleared() {
    rxDispose.clear()
    super.onCleared()
  }

  fun validateAndLogin(email: String, password: String) {
    // Validate
    if (email.isNullOrEmpty() || email.matches("[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+".toRegex())) {
      command.value = Command.Snack(resId = R.string.invalid_email)
      return
    }

    if (password.isNullOrEmpty()) {
      command.value = Command.Snack(resId = R.string.invalid_password_length)
      return
    }

    val dispose = userRepo.login(email, password)
        .map { response ->
          val jsonStr = errorHelper.parseResponse(response)
          val user = Gson().fromJson(jsonStr, RemoteUser::class.java)

          if (user.userId == null) {
            user.userId = 1001
          }
          if (user.email.isNullOrEmpty()) {
            user.email = email
            user.username = email
          }
          if (user.fullName.isNullOrEmpty()) {
            user.fullName = email.split("@").first()
          }
          if (user.sex.isNullOrEmpty()) {
            user.sex = "Nam"
          }
          if (user.address.isNullOrEmpty()) {
            user.address = "Ho Chi Minh"
          }
          if (user.phone.isNullOrEmpty()) {
            user.phone = ""
          }
          if (user.phone.isNullOrEmpty()) {
            user.birthday = ""
          }
          return@map user
        }
        .concatMapCompletable { user ->
          userRepo.rememberUser(user)

          val accInfo = AccountInfo(
              id = user.userId!!,
              userName = user.username!!,
              fullName = user.fullName!!,
              sex = user.sex!!,
              email = user.email!!,
              phone = user.phone!!,
              address = user.address!!,
              birthday = user.birthday!!,
              image = if (user.sex.equals("nam",ignoreCase = true)) drawable.ic_avatar_men else drawable.ic_avatar_women
          )
          userRepo.saveInfo(info = accInfo)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { isProcessing.value = true }
        .doFinally { isProcessing.value = false }
        .subscribe({

        }, { e ->
          logHelper.e(e)
          command.value = errorHelper.parse(e)
        })
    rxDispose.add(dispose)
  }
}