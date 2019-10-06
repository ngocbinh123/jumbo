package com.nnbinh.jumbo.obj

import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_ID
import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_NAME
import com.nnbinh.jumbo.obj.Constant.PREF_KEY_USER_TOKEN
import com.tumblr.remember.Remember

object CurrentUser {
  fun userId() = Remember.getInt(PREF_KEY_USER_ID, -1)

  fun userName() = Remember.getString(PREF_KEY_USER_NAME, "")

  fun token() = Remember.getString(PREF_KEY_USER_TOKEN, "")

  fun isSignedIn() : Boolean {
    return (userId() != -1 && userName().isNotEmpty() && token().isNotEmpty())
  }
}