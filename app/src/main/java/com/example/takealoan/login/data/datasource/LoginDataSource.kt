package com.example.takealoan.login.data.datasource

import com.example.takealoan.login.data.model.PostRegistrationModel
import io.reactivex.Single

interface LoginDataSource {

    fun login(username: String, password: String): Single<String>

    fun registration(username: String, password: String): Single<PostRegistrationModel>

}

