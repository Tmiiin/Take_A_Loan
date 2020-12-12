package com.example.takealoan.login.presentation.interfaces

import com.example.takealoan.login.ui.interfaces.RegistrationView

interface RegistrationPresenter {
    fun onDestroy()
    fun onRegistrationButtonClick(username: String, password: String)
    fun attachView(view: RegistrationView)
    fun isUsernameValid(username: String): Boolean
    fun isPasswordValid(password: String): Boolean
}