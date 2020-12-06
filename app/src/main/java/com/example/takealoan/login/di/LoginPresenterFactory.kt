package com.example.takealoan.login.di

import com.example.takealoan.login.data.datasource.LoginDataSourceImpl
import com.example.takealoan.login.domain.LoginUseCase
import com.example.takealoan.login.domain.RegistrationUseCase
import com.example.takealoan.login.presentation.LoginPresenter
import com.example.takealoan.login.presentation.LoginPresenterImpl

object LoginPresenterFactory {

    fun create(): LoginPresenter {
        val loginDataSource = LoginDataSourceImpl()
        val loginUseCase = LoginUseCase(loginDataSource)
        val registrationUseCase = RegistrationUseCase(loginDataSource)

        return LoginPresenterImpl(loginUseCase, registrationUseCase)
    }
}