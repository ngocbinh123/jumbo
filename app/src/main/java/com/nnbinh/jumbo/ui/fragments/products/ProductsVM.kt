package com.nnbinh.jumbo.ui.fragments.products

import com.nnbinh.jumbo.MissionViewModel
import com.nnbinh.jumbo.R.drawable
import com.nnbinh.jumbo.db.Product
import com.nnbinh.jumbo.repo.ProductsRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsVM @Inject constructor(productRepo: ProductsRepo): MissionViewModel() {
  val products by lazy { productRepo.getAll() }

  init {
    if (products.value.isNullOrEmpty()) {
      productRepo.saveAll(dummyProducts())
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({
          }, { e ->
            logHelper.e(e)
            command.value = errorHelper.parse(e)
          })
    }
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