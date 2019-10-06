package com.nnbinh.jumbo.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.helpers.prettyTime
import com.nnbinh.jumbo.ui.activities.root.RootActivity
import java.util.Date

class CheckInOutDlg: DialogFragment() {
  companion object {
    private const val KEY_SUPER_MARKET = "KEY_SUPER_MARKET"
    fun getInstance(superMarket: SuperMarket): CheckInOutDlg {
      return with(CheckInOutDlg()) {
        val bundle = Bundle()
        bundle.putSerializable(KEY_SUPER_MARKET, superMarket)
        this.arguments = bundle
        this
      }
    }
  }

  private val superMarket: SuperMarket? by lazy { arguments?.getSerializable(KEY_SUPER_MARKET) as? SuperMarket }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    isCancelable = false
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return DialogHelper.createConfirmDlg(context = context!!,
        title = getString(R.string.check_in_out),
        content = "${getString(R.string.check_in_out_at)}\n- ${getString(R.string.super_market)} ${superMarket?.name}\n- ${getString(R.string.time)} ${Date().prettyTime()}",
        cancelOnTouchOut = false,
        nameOfBtnLeft = getString(R.string.cancel),
        leftBtnListener = { dlg -> dlg.dismiss() },
        nameOfBtnRight = getString(R.string.submit),
        rightBtnListener = {dlg ->
          dlg.dismiss()
          onSubmitCheckInOutInfo() }
        )
  }

  private fun onSubmitCheckInOutInfo() {
    (context as? RootActivity)?.onSubmitCheckInOutInfo()
  }
}