package com.example.takealoan.login.data.datasource

import android.util.Log
import com.example.takealoan.login.data.model.PostRegistrationModel
import com.example.takealoan.login.data.model.UserModel
import com.example.takealoan.login.data.network.IRetrofit
import io.reactivex.Single

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