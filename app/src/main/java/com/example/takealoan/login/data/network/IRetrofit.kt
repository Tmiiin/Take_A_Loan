package com.example.takealoan.login.data.network

import com.example.takealoan.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class IRetrofit {

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private var service: LoginAPI = retrofit.create(LoginAPI::class.java)

    fun getRetrofitService(): LoginAPI {
        return this.service
    }
}