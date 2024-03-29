package com.nnbinh.jumbo.repo

import com.nnbinh.jumbo.db.Product
import com.nnbinh.jumbo.di.AppDB
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepo @Inject constructor() {
  @Inject lateinit var db: AppDB

  fun getAll() = db.productDao().getAll()

  fun saveAll(ls: List<Product>) = db.productDao().insertAll(*ls.toTypedArray())
}