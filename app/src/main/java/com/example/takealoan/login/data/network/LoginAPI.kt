package com.example.takealoan.login.data.network

import com.example.takealoan.login.data.model.PostRegistrationModel
import com.example.takealoan.login.data.model.UserModel
import io.reactivex.Single
import retrofit2.http.*

interface LoginAPI {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body userModel: UserModel): Single<String>

    @Headers("Content-Type: application/json")
    @POST("registration")
    fun registration(@Body userModel: UserModel): Single<PostRegistrationModel>
}