package com.polukhin.randevutest.di.retrofit

import com.polukhin.rentatest.model.Response
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("users")
    fun getUsers(): Single<Response>
}