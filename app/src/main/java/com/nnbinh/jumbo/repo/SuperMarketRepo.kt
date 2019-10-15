package com.nnbinh.jumbo.repo

import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.di.AppDB
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuperMarketRepo @Inject constructor() {
  @Inject lateinit var db: AppDB

  fun getAll() = db.superMarketDao().getAll()

  fun saveAll(ls: List<SuperMarket>) = db.superMarketDao().insertAll(*ls.toTypedArray())
}