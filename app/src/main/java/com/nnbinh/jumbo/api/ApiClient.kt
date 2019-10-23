package com.nnbinh.jumbo.api

import com.nnbinh.jumbo.helpers.LogHelper
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiClient @Inject constructor() {
  @Inject
  lateinit var logHelper: LogHelper
  private val API_URL = "https://ntgroupipamwebapi.azurewebsites.net/api/"

  private val retrofit: Retrofit by lazy {
    val builder = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
      val req = chain.request().newBuilder()
          .addHeader("Content-Type", "application/json'")
          .addHeader("User-Agent", System.getProperty("http.agent"))
          .build()
      return@addInterceptor chain.proceed(req)
    }


    builder.client(httpClient.build())
    builder.build()
  }

  private val service: ApiService by lazy {
    retrofit.create(ApiService::class.java)
  }

  fun login(email: String, password: String): Observable<ResponseBody> =
      service.login(email, password)
}