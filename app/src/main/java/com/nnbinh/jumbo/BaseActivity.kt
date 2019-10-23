package com.nnbinh.jumbo

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.helpers.LogHelper
import com.nnbinh.jumbo.helpers.withColor
import com.nnbinh.jumbo.ui.activities.login.LoginActivity
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
  @Inject lateinit var factory: ViewModelProvider.Factory
  @Inject lateinit var logHelper: LogHelper
  val viewmodel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { initViewModel() }
  protected abstract fun initViewModel() : ViewModel

  private var beepPlayer: MediaPlayer? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    logHelper.v("onCreate " + this::class.java)
  }

  override fun onResume() {
    super.onResume()
    logHelper.v("onResume " + this::class.java)
  }

  override fun onPause() {
    super.onPause()
    logHelper.v("onPause " + this::class.java)

    beepPlayer?.release()
    beepPlayer = null
  }

  override fun onDestroy() {
    super.onDestroy()
    logHelper.v("onDestroy " + this::class.java)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//    if (this is MainActivity) {
//      menuInflater.inflate(R.menu.menu_main, menu)
//    }
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when (item?.itemId) {
      android.R.id.home -> {
        onBackPressed()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  fun signOut() {
    (viewmodel as UserViewModel).signOut()
  }

  private fun backToLoginScreen() {
    val intent = Intent(this, LoginActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
  }

  override fun dispatchTouchEvent(event: MotionEvent): Boolean {
    if (event.action == MotionEvent.ACTION_DOWN) {
      val v = currentFocus
      if (v is EditText) {
        val outRect = Rect()
        v.getGlobalVisibleRect(outRect)
        if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
          //v.clearFocus()
          hideKeyboard()
        }
      }
    }
    return super.dispatchTouchEvent(event)
  }

  fun hideKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.window.decorView.applicationWindowToken, 0)
  }

  open fun processCommand(command: Command) {
    when (command) {
      is Command.Snack -> {
        showSnack(command)
        if (command.hasSound) playSound(command.isSucceed)
      }
      is Command.LogoutSuccess -> {
        backToLoginScreen()
      }
    }
  }

  private fun showSnack(command: Command.Snack) {
    val rootview = window.decorView.rootView


    val bgColor = if (command.isSucceed)
      ContextCompat.getColor(this, R.color.colorPrimaryDark)
    else
      ContextCompat.getColor(this, R.color.colorLightRed)

    val txtColor = ContextCompat.getColor(this, R.color.colorLightGrey)

    if (command.message != null)
      Snackbar.make(rootview!!, command.message, 5000)
          .withColor(bgColor, txtColor).show()
    else if (command.resId != null)
      Snackbar.make(rootview!!, command.resId, 5000)
          .withColor(bgColor, txtColor).show()
  }

  private fun playSound(isSucceed: Boolean) {
    try {
      beepPlayer = MediaPlayer.create(this, if (isSucceed) R.raw.succeed else R.raw.fail)
      beepPlayer?.start()
    } catch (e: Exception) {
      logHelper.e(e)
    }
  }
}