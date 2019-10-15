package com.nnbinh.jumbo.ui.fragments.account

import androidx.lifecycle.MutableLiveData
import com.nnbinh.jumbo.MissionViewModel
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.db.AccountInfo
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.obj.CurrentUser
import com.nnbinh.jumbo.repo.UserRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountVM @Inject constructor() : MissionViewModel() {
  @Inject lateinit var userRepo: UserRepo

  val accountInfo by lazy { userRepo.getInfo(CurrentUser.userId()) }

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

  private fun getAccountInfo(): AccountInfo? {
    val username = CurrentUser.userName()

    return when(username) {
      "huy.nguyen" -> AccountInfo(id = 101,
          userName = username,
          fullName = "Nguyễn Quốc Huy",
          sex = "Nam",
          email = "huy.nguyen@gmail.com",
          address = "Q. Tân Bình, TP. Hồ Chí Minh",
          phone = "0941444555",
          birthday = "20/06/1986",
          image = R.drawable.ic_avatar_men)
      "linh.tran" -> AccountInfo(id = 101,
          userName = username,
          fullName = "Trần Ái Linh",
          sex = "Nữ",
          email = "linh.tran@gmail.com",
          address = "Q.3, TP. Hồ Chí Minh",
          phone = "0921363159",
          birthday = "15/02/1994",
          image = R.drawable.ic_avatar_women)
      else -> null
    }
  }
}