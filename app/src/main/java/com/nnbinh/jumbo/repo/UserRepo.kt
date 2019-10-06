package com.nnbinh.jumbo.repo

import com.nnbinh.jumbo.di.AppDB
import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_ID
import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_NAME
import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_TOKEN
import com.tumblr.remember.Remember
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor() {
  @Inject lateinit var db: AppDB

  fun clearUser() {
    db.clearAllTables()

    Remember.remove(PREF_KEY_USER_ID)
    Remember.remove(PREF_KEY_USER_NAME)
    Remember.remove(PREF_KEY_USER_TOKEN)
  }
}