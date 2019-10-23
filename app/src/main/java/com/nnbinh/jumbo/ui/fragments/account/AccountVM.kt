package com.nnbinh.jumbo.ui.fragments.account

import com.nnbinh.jumbo.MissionViewModel
import com.nnbinh.jumbo.obj.CurrentUser
import com.nnbinh.jumbo.repo.UserRepo
import javax.inject.Inject

class AccountVM @Inject constructor() : MissionViewModel() {
  @Inject lateinit var userRepo: UserRepo

  val accountInfo by lazy { userRepo.getInfo(CurrentUser.userId()) }
}