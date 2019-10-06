package com.nnbinh.jumbo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AccountInfo")
class AccountInfo(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "fullName") var fullName: String,
    @ColumnInfo(name = "sex") var sex: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "phone") var phone: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "birthday") var birthday: String,
    @ColumnInfo(name = "image") var image: Int
) {
}