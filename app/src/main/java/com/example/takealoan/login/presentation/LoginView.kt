package com.example.takealoan.login.presentation

interface LoginView {

    fun showUserCreated()

    fun showLoading()

    fun hideLoading()

    fun showUsernamePasswordError()

    fun showAuthError()

    fun showUnacceptableNamePassword()
    fun registrationSuccess()
}