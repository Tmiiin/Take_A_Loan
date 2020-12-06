package com.example.takealoan.login.data.datasource

import android.util.Log
import com.example.takealoan.login.data.model.PostRegistrationModel
import com.example.takealoan.login.data.model.UserModel
import com.example.takealoan.login.data.network.IRetrofit
import com.example.takealoan.login.data.network.LoginAPI
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


interface LoginDataSource {


    fun login(username: String, password: String): Single<String>

    fun registration(username: String, password: String): Single<PostRegistrationModel>

}

class LoginDataSourceImpl : LoginDataSource {

    companion object {
        val service = IRetrofit().getRetrofitService()
    }
     val TAG = "LoginDataSource"

    override fun login(username: String, password: String): Single<String> =
        try {
            Log.i(TAG, UserModel(name = username, password = password).toString())
            service.login(UserModel(name = username, password = password))
        } catch (e: Exception) {
            throw Exception(e.message)
        }


    override fun registration(username: String, password: String): Single<PostRegistrationModel> =
        try {
            Log.i(TAG, UserModel(name = username, password = password).toString())
            service.registration(UserModel(name = username, password = password))
        } catch (e: Exception) {
            throw Exception(e.message)
        }


}