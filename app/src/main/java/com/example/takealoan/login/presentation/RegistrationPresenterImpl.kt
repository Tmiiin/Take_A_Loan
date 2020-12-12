package com.example.takealoan.login.presentation

import android.util.Log
import com.example.takealoan.Constants
import com.example.takealoan.login.data.model.PostRegistrationModel
import com.example.takealoan.login.domain.RegistrationUseCase
import com.example.takealoan.login.presentation.interfaces.RegistrationPresenter
import com.example.takealoan.login.ui.interfaces.RegistrationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class RegistrationPresenterImpl(
    private val registrationUseCase: RegistrationUseCase
) : RegistrationPresenter {

    private var view: RegistrationView? = null
    var myDisposable: Disposable? = null
    val TAG: String = "RegistrationPresenterImpl"


    override fun attachView(view: RegistrationView) {
        this.view = view
    }

    override fun isUsernameValid(username: String): Boolean {
        return username.length > 5
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    override fun onDestroy() {
        this.myDisposable?.dispose()
        this.myDisposable = null
        this.view = null
    }

    override fun onRegistrationButtonClick(username: String, password: String) {
        try {
            myDisposable = registrationUseCase(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ success -> handleRegistrationResponse(success) },
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

    private fun handleRegistrationResponse(msg: PostRegistrationModel) {
        Log.i(TAG, msg.toString())
        view?.registrationSuccess()
    }
}