package com.nnbinh.jumbo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface SuperMarketDao {
  @Query("SELECT * FROM SuperMarket")
  fun getAll(): LiveData<List<SuperMarket>>
}