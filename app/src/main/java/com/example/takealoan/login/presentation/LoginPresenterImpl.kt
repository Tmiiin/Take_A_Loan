package com.example.takealoan.login.presentation

import android.util.Log
import com.example.takealoan.Constants
import com.example.takealoan.login.domain.LoginUseCase
import com.example.takealoan.login.presentation.interfaces.LoginPresenter
import com.example.takealoan.login.ui.interfaces.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class LoginPresenterImpl(
    private val loginUseCase: LoginUseCase
) : LoginPresenter {

    private var view: LoginView? = null
    var myDisposable: Disposable? = null
    val TAG: String = "LoginPresenterImpl"

    override fun attachView(view: LoginView) {
        this.view = view
    }


    override fun isUsernameValid(username: String): Boolean {
        return username.length > 5
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    override fun onLoginButtonClick(username: String, password: String) {
        try {
            myDisposable = loginUseCase(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ success -> handleLoginResponse(success) },
                    { error -> doOnError(error) })
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            view?.showToastError(Constants.error2)
        }
    }

    private fun doOnError(e: Throwable) {
        Log.e(TAG, e.message.toString())
        view?.showToastError(Constants.error3)
    }

    private fun handleLoginResponse(msg: String) {
        Log.i(TAG, "token is: $msg")
        view?.loginSuccess(msg)
    }

    override fun onDestroy() {
        this.myDisposable?.dispose()
        this.myDisposable = null
        this.view = null
    }
}