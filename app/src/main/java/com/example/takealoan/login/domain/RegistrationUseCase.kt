package com.example.takealoan.login.domain

import com.example.takealoan.login.data.datasource.LoginDataSource
import com.example.takealoan.login.data.model.PostRegistrationModel
import io.reactivex.Single


class RegistrationUseCase(private val loginRepository: LoginDataSource) {

    operator fun invoke(username: String, password: String): Single<PostRegistrationModel> =
        loginRepository.registration(username, password)

}