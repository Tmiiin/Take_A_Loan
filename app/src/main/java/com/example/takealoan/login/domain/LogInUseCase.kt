package com.example.takealoan.login.domain

import com.example.takealoan.login.domain.repository.LoginRepository
import io.reactivex.Single


class LoginUseCase(private val loginRepository: LoginRepository) {

    operator fun invoke(username: String, password: String): Single<String> {
        return loginRepository.login(username, password)
    }
}