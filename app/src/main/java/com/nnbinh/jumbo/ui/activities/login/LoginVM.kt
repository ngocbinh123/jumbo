package com.nnbinh.jumbo.ui.activities.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.R.drawable
import com.nnbinh.jumbo.db.AccountInfo
import com.nnbinh.jumbo.db.Product
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.event.SingleLiveEvent
import com.nnbinh.jumbo.obj.Constant
import com.nnbinh.jumbo.obj.Constant.PASSWORD_MIN_LENGTH
import com.nnbinh.jumbo.obj.Constant.USERNAME_MAX_LENGTH
import com.nnbinh.jumbo.obj.Constant.USERNAME_MIN_LENGTH
import com.nnbinh.jumbo.repo.UserRepo
import com.tumblr.remember.Remember
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.Calendar
import javax.inject.Inject

class LoginVM @Inject constructor(val userRepo: UserRepo) : ViewModel() {
  val command: SingleLiveEvent<Command> = SingleLiveEvent()
  val isProcessing = MutableLiveData(false)
  val rxDispose = CompositeDisposable()

  override fun onCleared() {
    rxDispose.clear()
    super.onCleared()
  }

  fun validateAndLogin(username: String, password: String) {
    // Validate
    if (username.length < USERNAME_MIN_LENGTH || username.length > USERNAME_MAX_LENGTH) {
      command.value = Command.Snack(resId = R.string.invalid_username_length)
      return
    }

    if (password.length < PASSWORD_MIN_LENGTH) {
      command.value = Command.Snack(resId = R.string.invalid_password_length)
      return
    }

    if (!password.matches("^[0-9]+$".toRegex())) {
      command.value = Command.Snack(resId = R.string.invalid_password_character)
      return
    }

    val dispose = Observable.fromCallable {

      val accountInfo = when {
        username == "huy.nguyen" && password == "12345678" -> AccountInfo(id = 101,
            userName = username,
            fullName = "Nguyễn Quốc Huy",
            sex = "Nam",
            email = "huy.nguyen@gmail.com",
            address = "Q. Tân Bình, TP. Hồ Chí Minh",
            phone = "0941444555",
            birthday = "20/06/1986",
            image = R.drawable.ic_avatar_men)
        username == "linh.tran" && password == "87654321" -> AccountInfo(id = 101,
              userName = username,
              fullName = "Trần Ái Linh",
              sex = "Nữ",
              email = "linh.tran@gmail.com",
              address = "Q.3, TP. Hồ Chí Minh",
              phone = "0921363159",
              birthday = "15/02/1994",
              image = R.drawable.ic_avatar_women)
        else -> null
      }

      if (accountInfo != null) {
        val superMarkets = dummySuperMarkets()
        val products = dummyProducts()

        userRepo.saveSuperMarkets(superMarkets)
        userRepo.saveProducts(products)

        Remember.putInt(Constant.PREF_KEY_USER_ID, accountInfo.id)
        Remember.putString(Constant.PREF_KEY_USER_NAME, accountInfo.userName)
        Remember.putString(Constant.PREF_KEY_USER_TOKEN, "jumbo_${username}_${Calendar.getInstance().timeInMillis}")
        userRepo.saveInfo(accountInfo)

        return@fromCallable true
      }

      return@fromCallable false

    }.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnSubscribe { isProcessing.value = true }
        .doFinally { isProcessing.value = false }
        .subscribe {isSuccess ->
          if (!isSuccess) {
            command.value = Command.Snack(resId = R.string.user_name_or_password_is_wrong)
          }
        }
    rxDispose.add(dispose)
  }

  private fun dummySuperMarkets(): List<SuperMarket> {
    var  randomIndex = 1001
    return arrayListOf(
        SuperMarket( id = randomIndex++, image = drawable.img_coop_xlhn,name = "Co.opmart Xa Lộ Hà Nội", address = "191 Quang Trung", ward = "Hiệp Phú", district = "9", city = "HCM"),
        SuperMarket( id = randomIndex++, image = drawable.img_coop_binh_trieu,name = "Co.opmart Bình Triệu", address = "Đường số 10", ward = "Hiệp Bình Chánh", district = "Thủ Đức", city = "HCM"),
        SuperMarket( id = randomIndex++, image = drawable.img_coop_van_thanh, name = "Co.opmart Văn Thánh", address = "561A Điện Biên Phủ", ward = "25", district = "Bình Thạnh", city = "HCM"),
        SuperMarket( id = randomIndex++, image = drawable.img_coop_ndc, name = "Co.opmart Nguyễn Đình Chiểu", address = "168 Nguyễn Đình Chiểu", ward = "6", district = "3", city = "HCM"),
        SuperMarket( id = randomIndex++, image = drawable.img_coop_pvt, name = "Co.opmart Phan Văn Trị", address = "543/1 Phan Văn Trị", ward = "7", district = "Gò Vấp", city = "HCM"),
        SuperMarket( id = randomIndex++, image = drawable.img_coop_go_vap, name = "Co.opmart Gò Vấp", address = "304A Quang Trung", ward = "11", district = "Gò Vấp", city = "HCM"),
        SuperMarket( id = randomIndex++, image = drawable.img_coop_rach_mieu, name = "Co.opmart Rạch Miễu", address = "48 Hoa Sứ", ward = "7", district = "Phú Nhuận", city = "HCM")
    )
  }

  private fun dummyProducts(): List<Product> {
    var  randomIndex = 1001
    return arrayListOf(
        Product(id = randomIndex++, name = "Miếng Đuổi Muỗi Jumbo Hương Hoa 25g", price = 22000f, total = 102, remainNumber = 16, image = drawable.img_box, description = "Miếng Đuổi Muỗi Jumbo Hương Hoa 25g mang đến cho bạn và gia đình không gian thông thoáng, đuổi sạch muỗi và côn trùng gây hại. Hương hoa thơm ngọt đem lại cảm giác thoải mái dễ chịu. Đặc biệt thành phần không gây hại cho sức khỏe của cả gia đình."),
        Product(id = randomIndex++, name = "Bình Xịt Muỗi Jumbo Vape FIK Hương Cam Chanh 100712620 (600ml)", price = 54000f, total = 102, remainNumber = 75, image = drawable.img_box, description = "Xịt Jumbo FIK Hương Chanh 600ml với công thức hiệu quả giúp đẩy lùi những loại côn trùng gây hại như kiến, gián và muỗi. Giúp bạn gạt bỏ mọi nỗi lo về các chứng bệnh do côn trùng gây ra như sốt xuất huyết, nguồn bệnh từ gián, kiến khoang,.."),
        Product(id = randomIndex++, name = "Bình Xịt Côn Trùng Jumbo Vape Super A1 Hương Lavender 100712618 (600ml)", price = 54000f, total = 100, remainNumber = 42, image = drawable.img_box, description = "Xịt Jumbo Super A1 Hương Lavender 680ml với công thức hiệu quả giúp đẩy lùi những loại côn trùng gây hại như kiến, gián và muỗi. Giúp bạn gạt bỏ mọi nỗi lo về các chứng bệnh do côn trùng gây ra như sốt xuất huyết, nguồn bệnh từ gián, kiến khoang,.."),
        Product(id = randomIndex++, name = "Bình Xịt Côn Trùng - Jumbo Vape - Multi - Insect Killer - Hương Cam Chanh Tự Nhiên (600ml)", price = 70000f, total = 100, remainNumber = 42, image = drawable.img_box, description = "Bình Xịt Côn Trùng - Jumbo Vape - Multi - Insect Killer - Hương Cam Chanh Tự Nhiên (600ml) với hương chanh tươi mát sẽ  mang đến cho bạn và gia đình không gian thông thoáng, đuổi sạch ruồi muỗi và côn trùng. Với công thức tiên tiến đuổi sạch mọi loại côn trùng gây khó chịu cho cả gia đình, trả lại sự dễ chịu, bình yên cho bạn và người thân."),
        Product(id = randomIndex++, name = "Xịt Jumbo H20 Hương Lily 100377590 (680ml)", price = 54000f, total = 100, remainNumber = 67, image = drawable.img_box, description = "Xịt Jumbo H20 Hương Lily 680ml với công thức hiệu quả giúp đẩy lùi những loại côn trùng gây hại như kiến, gián và muỗi. Giúp bạn gạt bỏ mọi nỗi lo về các chứng bệnh do côn trùng gây ra như sốt xuất huyết, nguồn bệnh từ gián, kiến khoang,.."),
        Product(id = randomIndex++, name = "Bộ xông đuổi muỗi Jumbo Vape", price = 53900f, total = 150, remainNumber = 67, image = drawable.img_box, description = "ướng dẫn sử dụng:\n" + "\n" + "- Nhẹ nhàng tách 02 khoanh nhang thành 02 khoanh nhang đơn tại trung tâm bằng các đầu ngón tay.\n" + "\n" + "- Gắn khoanh nhang đơn vào giá và đốt đầu ngoài khoanh nhang, sau đó thổi tắt lửa.\n" + "\n" + "- Nếu có khoanh nhang bị gãy, có thể gắn vào đầu giá đỡ.\n" + "\n" + "Bảo quản nơi khô ráo, tránh ánh nắng trực tiếp chiếu vào."),
        Product(id = randomIndex++, name = "Nhang Muỗi Jumbo Vape Super V Lavender", price = 10000f, total = 100, remainNumber = 12, image = drawable.img_box, description = "ướng dẫn sử dụng:\n" + "\n" + "- Nhẹ nhàng tách 02 khoanh nhang thành 02 khoanh nhang đơn tại trung tâm bằng các đầu ngón tay.\n" + "\n" + "- Gắn khoanh nhang đơn vào giá và đốt đầu ngoài khoanh nhang, sau đó thổi tắt lửa.\n" + "\n" + "- Nếu có khoanh nhang bị gãy, có thể gắn vào đầu giá đỡ.\n" + "\n" + "Bảo quản nơi khô ráo, tránh ánh nắng trực tiếp chiếu vào."),
        Product(id = randomIndex++, name = "CHẤT XÔNG ĐUỔI MUỖI JUMBO VAPE HƯƠNG HOA LILY", price = 29400f, total = 140, remainNumber = 112, image = drawable.img_box, description = "ướng dẫn sử dụng:\n" + "\n" + "- Nhẹ nhàng tách 02 khoanh nhang thành 02 khoanh nhang đơn tại trung tâm bằng các đầu ngón tay.\n" + "\n" + "- Gắn khoanh nhang đơn vào giá và đốt đầu ngoài khoanh nhang, sau đó thổi tắt lửa.\n" + "\n" + "- Nếu có khoanh nhang bị gãy, có thể gắn vào đầu giá đỡ.\n" + "\n" + "Bảo quản nơi khô ráo, tránh ánh nắng trực tiếp chiếu vào.")
    )
  }
}