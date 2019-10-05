package com.nnbinh.jumbo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SuperMarket")

class SuperMarket(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "ward") var ward: String,
    @ColumnInfo(name = "district") var district: String,
    @ColumnInfo(name = "city") var city: String
    ) {
}