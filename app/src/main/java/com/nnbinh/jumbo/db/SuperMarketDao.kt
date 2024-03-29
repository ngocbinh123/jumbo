package com.nnbinh.jumbo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable

@Dao
interface SuperMarketDao {
  @Query("SELECT * FROM SuperMarket")
  fun getAll(): LiveData<List<SuperMarket>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(vararg item: SuperMarket): Completable

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(item: SuperMarket): Completable

  @Delete
  fun delete(item: SuperMarket)

  @Update
  fun update(vararg item: SuperMarket)
}