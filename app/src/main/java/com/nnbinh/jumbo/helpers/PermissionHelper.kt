package com.nnbinh.jumbo.helpers

import android.Manifest
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.ref.WeakReference


class PermissionHelper() : EasyPermissions.PermissionCallbacks {
  constructor(frag: Fragment) : this() {
    this.fragRef = WeakReference(frag)
    this.reference = WeakReference(frag.activity!!)
    isFragment = true
  }

  constructor(activity: FragmentActivity) : this() {
    this.reference = WeakReference(activity)
    isFragment = false
  }

  companion object {
    const val PER_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    val PER_CAMERA = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    const val REQUEST_CODE_START_CAMERA = 1212
    const val REQUEST_CODE_WRITE_STORAGE = 1218

    const val REQUEST_CODE_ASK_CAMERA_ONLY = 3218
    const val REQUEST_CODE_ASK_STORAGE_ONLY = 3220
  }

  private lateinit var reference: WeakReference<FragmentActivity>
  private lateinit var fragRef: WeakReference<Fragment>
  private var isFragment = false
  private var identifyNumber = 0
  fun shouldOpenAppSettingsDialog(requestCode: Int) {
    if (reference.get() == null) return
    AppSettingsDialog.Builder(reference.get()!!)
        .setRequestCode(requestCode)
        .build().show()
  }

  fun hasPermission(vararg permissions: String): Boolean {
    return if (reference.get() == null)
      return false
    else EasyPermissions.hasPermissions(reference.get()!!, *permissions)
  }

  fun isWritePermissionGranted(): Boolean {
    return hasPermission(PermissionHelper.PER_STORAGE)
  }

  /**
   *  must let wrapper handle on request permission result
   */
  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
      grantResults: IntArray) {
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
  }

  /**
   *  callback
   */
  override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    doAction(requestCode)
  }

  override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    val context = if (isFragment) {
      fragRef.get()?.activity ?: return
    } else {
      reference.get() ?: return
    }

    //check if user check have never ask again
    if (EasyPermissions.somePermissionPermanentlyDenied(context, perms)) {
      shouldOpenAppSettingsDialog(requestCode)
    } else {
      doAction(requestCode)
    }
  }

  /**
   *  when use change permission in device Setting, this fun should be call
   */
  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    doAction(requestCode)
  }

  private fun doAction(requestCode: Int) {
    val context = reference.get() ?: return
    when (requestCode) {
      REQUEST_CODE_START_CAMERA -> {
        if (context is CameraPermissionCallBack) {
          if (hasPermission(*PER_CAMERA)) context.onCameraGrant(identifyNumber)
          else context.onCameraDeny(identifyNumber)
        }

        if (isFragment) {
          val frag = fragRef.get()
          if (frag is CameraPermissionCallBack)
            if (hasPermission(*PER_CAMERA)) frag.onCameraGrant(identifyNumber)
            else frag.onCameraDeny(identifyNumber)
        }
      }

      REQUEST_CODE_WRITE_STORAGE -> {
        if (context is StoragePermissionCallBack) {
          if (hasPermission(PER_STORAGE)) context.onStorageGrant(identifyNumber)
          else context.onStorageDeny(identifyNumber)
        }

        if (isFragment) {
          val frag = fragRef.get()
          if (frag is StoragePermissionCallBack)
            if (hasPermission(PER_STORAGE)) frag.onStorageGrant(identifyNumber)
            else frag.onStorageDeny(identifyNumber)
        }
      }
    }
  }

  interface CameraPermissionCallBack{
    fun onCameraGrant(identifyNumber : Int)
    fun onCameraDeny(identifyNumber : Int)
  }
  interface StoragePermissionCallBack {
    fun onStorageGrant(identifyNumber : Int)
    fun onStorageDeny(identifyNumber : Int)
  }
}