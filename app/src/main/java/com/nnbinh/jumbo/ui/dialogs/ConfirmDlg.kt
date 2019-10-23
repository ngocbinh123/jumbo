package com.nnbinh.jumbo.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.ui.activities.root.RootActivity

class ConfirmDlg: DialogFragment() {

  companion object {
    const val MODE_LOGOUT_BY_USER = 100
    private const val KEY_MODE = "KEY_MODE"

    fun getNewInstance(mode: Int): ConfirmDlg {
      return with(ConfirmDlg()) {
        val bundle = Bundle()
        bundle.putInt(KEY_MODE, mode)
        this.arguments = bundle
        this
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    isCancelable = false
  }

  private val wMode: Int by lazy { arguments?.getInt(KEY_MODE)?:-1 }
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return when(wMode) {
      MODE_LOGOUT_BY_USER -> buildSignOutDlg()
      else-> AlertDialog.Builder(activity).create()
    }
  }

  private fun buildSignOutDlg(): AlertDialog {
    return DialogHelper.createConfirmDlg(
        context = context!!,
        title = getString(R.string.sign_out),
        content = getString(R.string.msg_sign_out),
        nameOfBtnLeft = getString(R.string.cancel),
        leftBtnListener = { dlg ->  dlg.dismiss() },
        nameOfBtnRight = getString(R.string.ok),
        rightBtnListener = { dlg ->
          dlg.dismiss()
          (activity as? RootActivity)?.signOut()
        }
    )
  }
}