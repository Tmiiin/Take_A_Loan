package com.example.takealoan.login.presentation

interface LoginPresenter {

    fun attachView(view: LoginView)

    fun onRegistrationButtonClick(username: String, password: String)

    fun onDestroy()

    fun onLoginButtonClick(username: String, password: String)
    fun isUsernameValid(username: String): Boolean
    fun isPasswordValid(password: String): Boolean
}