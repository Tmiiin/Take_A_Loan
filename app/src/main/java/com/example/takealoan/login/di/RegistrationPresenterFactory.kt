package com.example.takealoan.login.di

import com.example.takealoan.login.data.datasource.LoginDataSourceImpl
import com.example.takealoan.login.data.repository.LoginRepositoryImpl
import com.example.takealoan.login.domain.RegistrationUseCase
import com.example.takealoan.login.presentation.interfaces.RegistrationPresenter
import com.example.takealoan.login.presentation.RegistrationPresenterImpl

object RegistrationPresenterFactory {
    fun create(): RegistrationPresenter {
        val loginDataSource = LoginDataSourceImpl()
        val loginRepository = LoginRepositoryImpl(loginDataSource)
        val registrationUseCase = RegistrationUseCase(loginRepository)

        return RegistrationPresenterImpl(registrationUseCase)
    }
}