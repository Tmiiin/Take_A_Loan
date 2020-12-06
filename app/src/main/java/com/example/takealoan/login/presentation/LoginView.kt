package com.example.takealoan.login.presentation

interface LoginView {

    fun showUserCreated()

    fun showLoading()

    fun hideLoading()

    fun registrationSuccess()
    fun loginSuccess(token: String)
    fun showToastError(error: String)
}