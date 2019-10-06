package com.nnbinh.jumbo.ui.fragments.suppermarkets

import androidx.lifecycle.MutableLiveData
import com.nnbinh.jumbo.MissionViewModel
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.event.Command
import javax.inject.Inject

class SupperMarketsVM @Inject constructor(): MissionViewModel() {
  val supermarkets = MutableLiveData<List<SuperMarket>>(dummySuperMarkets())

  private fun dummySuperMarkets(): List<SuperMarket> {
    return arrayListOf(
        SuperMarket( image = R.drawable.img_coop_xlhn,name = "Co.opmart Xa Lộ Hà Nội", address = "191 Quang Trung", ward = "Hiệp Phú", district = "9", city = "HCM"),
        SuperMarket( image = R.drawable.img_coop_binh_trieu,name = "Co.opmart Bình Triệu", address = "Đường số 10", ward = "Hiệp Bình Chánh", district = "Thủ Đức", city = "HCM"),
        SuperMarket( image = R.drawable.img_coop_van_thanh, name = "Co.opmart Văn Thánh", address = "561A Điện Biên Phủ", ward = "25", district = "Bình Thạnh", city = "HCM"),
        SuperMarket( image = R.drawable.img_coop_ndc, name = "Co.opmart Nguyễn Đình Chiểu", address = "168 Nguyễn Đình Chiểu", ward = "6", district = "3", city = "HCM"),
        SuperMarket( image = R.drawable.img_coop_pvt, name = "Co.opmart Phan Văn Trị", address = "543/1 Phan Văn Trị", ward = "7", district = "Gò Vấp", city = "HCM"),
        SuperMarket( image = R.drawable.img_coop_go_vap, name = "Co.opmart Gò Vấp", address = "304A Quang Trung", ward = "11", district = "Gò Vấp", city = "HCM"),
        SuperMarket( image = R.drawable.img_coop_rach_mieu, name = "Co.opmart Rạch Miễu", address = "48 Hoa Sứ", ward = "7", district = "Phú Nhuận", city = "HCM")
    )
  }

  fun onSubmitWorkingTime() {
    command.value = Command.Snack(resId = R.string.check_in_out_successful, isSucceed = true)
  }
}