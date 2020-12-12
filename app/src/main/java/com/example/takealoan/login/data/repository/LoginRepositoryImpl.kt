package com.example.takealoan.login.data.repository

import com.example.takealoan.login.data.datasource.LoginDataSource
import com.example.takealoan.login.data.model.PostRegistrationModel
import com.example.takealoan.login.domain.repository.LoginRepository
import io.reactivex.Single


class LoginRepositoryImpl(private val dataSource: LoginDataSource) : LoginRepository {

    override fun login(username: String, password: String): Single<String> =
        dataSource.login(username, password)

    override fun registration(username: String, password: String): Single<PostRegistrationModel> =
        dataSource.registration(username, password)

}