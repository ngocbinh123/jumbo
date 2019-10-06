package com.nnbinh.jumbo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nnbinh.jumbo.R

@Entity(tableName = "Product")
class Product(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "price") var price: Float,
    @ColumnInfo(name = "total") var total: Int,
    @ColumnInfo(name = "remainNumber") var remainNumber: Int,
    @ColumnInfo(name = "image") var image: Int = R.drawable.img_box
){
  fun getRemainingNumber(): String = "${remainNumber}/$total"
  fun getVNPrice(): String = "$price đ"
}