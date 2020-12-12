package com.example.takealoan.login.domain.repository

import com.example.takealoan.login.data.model.PostRegistrationModel
import io.reactivex.Single

interface LoginRepository {

    fun login(username: String, password: String): Single<String>

    fun registration(username: String, password: String): Single<PostRegistrationModel>
}