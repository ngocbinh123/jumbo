package com.nnbinh.jumbo.di

import android.app.Application
import androidx.room.Room
import com.nnbinh.jumbo.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
  @Provides
  @Singleton
  fun provideApp(app: BaseApp): Application = app

  @Provides
  @Singleton
  fun provideDb(app: BaseApp): AppDB {
    return Room.databaseBuilder(app, AppDB::class.java, "jumbo.db")
      .fallbackToDestructiveMigration()
      .build()
  }
}