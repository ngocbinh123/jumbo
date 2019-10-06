package com.nnbinh.jumbo.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nnbinh.jumbo.db.AccountInfo
import com.nnbinh.jumbo.db.AccountInfoDao
import com.nnbinh.jumbo.db.Product
import com.nnbinh.jumbo.db.ProductDao
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.db.SuperMarketDao

@Database(
    entities = [AccountInfo::class, SuperMarket::class, Product::class],
    version = 3
)
abstract class AppDB: RoomDatabase() {
  abstract fun accountInfoDao(): AccountInfoDao
  abstract fun superMarketDao(): SuperMarketDao
  abstract fun productDao(): ProductDao
}