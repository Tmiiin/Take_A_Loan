package com.example.takealoan.login.domain

import com.example.takealoan.login.data.datasource.LoginDataSource
import io.reactivex.Single


class LoginUseCase(private val loginRepository: LoginDataSource) {

    operator fun invoke(username: String, password: String): Single<String> {
        return loginRepository.login(username, password)
    }
}