package com.nnbinh.jumbo.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.nnbinh.jumbo.R

object DialogHelper {
  /**
   * create alert dialog include title, content and 2 buttons.
   * If you want to show a button, you should pass a button name for left button or right button
   * @param title: dialog title (required)
   * @param content: dialog title (required)
   * @param cancelOnTouchOut : allow user dismiss dlg by touched out. Default: true
   * @param nameOfBtnLeft : name of left button
   * @param nameOfBtnRight : name of right button. If you not pass value, the button is been invisible.
   * @param leftBtnListener : callback for left button
   * @param rightBtnListener : callback for right button
   * */
  fun createConfirmDlg(context: Context,
      title: String, content: String,
      cancelOnTouchOut: Boolean = true,
      nameOfBtnLeft: String?= null, leftBtnListener: (dlg: Dialog) -> Unit = {},
      nameOfBtnRight: String? = null, rightBtnListener: (dlg: Dialog) -> Unit = {}): AlertDialog {

    return with(AlertDialog.Builder(context).create()) {
      this.setCanceledOnTouchOutside(cancelOnTouchOut)

      val view = this.layoutInflater.inflate(R.layout.layout_dlg_2_acts, null)
      val tvTitle = view.findViewById<TextView>(R.id.tvDlgTitle)
      val tvContent = view.findViewById<TextView>(R.id.tvDlgContent)

      val btnLeft = view.findViewById<Button>(R.id.btnLeft)
      val btnRight = view.findViewById<Button>(R.id.btnRight)

      val vActLine = view.findViewById<View>(R.id.vActLine)

      tvTitle.text = title
      tvContent.text = content

      btnLeft.visibility = if (nameOfBtnLeft.isNullOrEmpty()) {
        View.INVISIBLE
      } else {
        btnLeft.text = nameOfBtnLeft
        btnLeft.setOnClickListener {
          leftBtnListener(this)
        }
        View.VISIBLE
      }

      btnRight.visibility = if (nameOfBtnRight.isNullOrEmpty()) {
        View.INVISIBLE
      } else {
        btnRight.text = nameOfBtnRight
        btnRight.setOnClickListener {
          rightBtnListener(this)
        }
        View.VISIBLE
      }

      vActLine.visibility = if (btnLeft.visibility == View.VISIBLE && btnRight.visibility == View.VISIBLE) {
        View.VISIBLE
      } else {
        View.INVISIBLE
      }

      this.setView(view)
      this
    }
  }
}