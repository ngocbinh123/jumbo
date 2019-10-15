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
interface AccountInfoDao {
  @Query("SELECT * FROM AccountInfo WHERE id = :userId LIMIT 1")
  fun getById(userId: Int): LiveData<AccountInfo>

  @Insert
  fun insert(item: AccountInfo): Completable

  @Delete
  fun delete(item: AccountInfo)

  @Update
  fun update(vararg item: AccountInfo)
}