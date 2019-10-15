package com.nnbinh.jumbo.repo

import android.util.Log
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.api.ApiClient
import com.nnbinh.jumbo.db.AccountInfo
import com.nnbinh.jumbo.db.Product
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.di.AppDB
import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_ID
import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_NAME
import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_TOKEN
import com.nnbinh.jumbo.obj.RemoteUser
import com.tumblr.remember.Remember
import io.reactivex.Observable
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor() {
  @Inject lateinit var db: AppDB

  @Inject lateinit var apiClient: ApiClient


  fun clearUser() {
    db.clearAllTables()

    Remember.remove(PREF_KEY_USER_ID)
    Remember.remove(PREF_KEY_USER_NAME)
    Remember.remove(PREF_KEY_USER_TOKEN)
  }

  fun getInfo(id: Int) = db.accountInfoDao().getById(id)

  fun saveInfo(info: AccountInfo) = db.accountInfoDao().insert(info)

  fun saveSuperMarkets(ls: List<SuperMarket>) = db.superMarketDao().insertAll(*ls.toTypedArray())

  fun saveProducts(ls: List<Product>) = db.productDao().insertAll(*ls.toTypedArray())

  fun login(email: String, password: String) : Observable<ResponseBody> {
    return apiClient.login(email, password)
  }

  fun rememberUser(user: RemoteUser) {
    if (user.userId != null && user.username != null && user.token != null) {
      Remember.putInt(PREF_KEY_USER_ID, user.userId!!)
      Remember.putString(PREF_KEY_USER_NAME, user.email)
      Remember.putString(PREF_KEY_USER_TOKEN, user.token)
      return
    }
    throw Exception("Can not save user credentials")
  }
}