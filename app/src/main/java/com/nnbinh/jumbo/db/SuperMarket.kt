package com.nnbinh.jumbo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nnbinh.jumbo.R
import java.io.Serializable

@Entity(tableName = "SuperMarket")

class SuperMarket(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "ward") var ward: String,
    @ColumnInfo(name = "district") var district: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "image") var image: Int = R.drawable.bg_login

    ): Serializable {
  override fun toString(): String {
    return "${address}, P.${ward}, Q. ${district}, ${city}"
  }
}