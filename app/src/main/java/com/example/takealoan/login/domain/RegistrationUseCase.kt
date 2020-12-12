package com.example.takealoan.login.domain

import com.example.takealoan.login.data.model.PostRegistrationModel
import com.example.takealoan.login.domain.repository.LoginRepository
import io.reactivex.Single


class RegistrationUseCase(private val loginRepository: LoginRepository) {

    operator fun invoke(username: String, password: String): Single<PostRegistrationModel> =
        loginRepository.registration(username, password)

}