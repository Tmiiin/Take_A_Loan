package com.example.takealoan.login.presentation.interfaces

import com.example.takealoan.login.ui.interfaces.LoginView

interface LoginPresenter{
    fun onDestroy()
    fun onLoginButtonClick(username: String, password: String)
    fun isUsernameValid(username: String): Boolean
    fun isPasswordValid(password: String): Boolean

    fun attachView(view: LoginView)
}