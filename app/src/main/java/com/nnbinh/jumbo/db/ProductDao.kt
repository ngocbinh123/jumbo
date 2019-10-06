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
interface ProductDao {
  @Query("SELECT * FROM Product")
  fun getAll(): LiveData<List<Product>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(vararg item: Product): Completable

  @Delete
  fun delete(item: Product)

  @Update
  fun update(vararg item: Product)
}