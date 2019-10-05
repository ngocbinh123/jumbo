package com.nnbinh.jumbo.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.db.SuperMarketDao

@Database(
    entities = [SuperMarket::class],
    version = 1
)
abstract class AppDB: RoomDatabase() {
  abstract fun superMarketDao(): SuperMarketDao
}