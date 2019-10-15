package com.nnbinh.jumbo.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    @FormUrlEncoded
    fun login(@Field("Email") username: String,
               @Field("Password") password: String
    ): Observable<ResponseBody>

}