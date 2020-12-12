package com.example.takealoan.login.ui.interfaces

interface LoginView: BaseLoginView{

    fun loginSuccess(token: String)

}