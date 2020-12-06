package com.example.takealoan.login.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.takealoan.Constants
import com.example.takealoan.R
import com.example.takealoan.loanscreen.ui.LoanView
import com.example.takealoan.Preferences
import com.example.takealoan.login.di.LoginPresenterFactory
import com.example.takealoan.login.presentation.LoginPresenter
import com.example.takealoan.login.presentation.LoginView
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity : AppCompatActivity(), LoginView {

    private var presenter: LoginPresenter? = null
    val TAG = "LoginView"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initPresenter()
        goOnLoginView()
    }

    override fun loginSuccess(token: String) {
        Preferences(this).setToken(token)
        Log.i(TAG, "Token is: $token")
        Toast.makeText(
            applicationContext, "Enter success!",
            Toast.LENGTH_LONG
        ).show()
        val intent = Intent(this@MainActivity, LoanView::class.java)
        startActivity(intent)
    }

    private fun initPresenter() {
        presenter = LoginPresenterFactory.create()
        presenter?.attachView(this)
    }

    private fun onLoginButtonClick() {
        showLoading()
        val username = username.text.toString()
        val password = password.text.toString()
        if (presenter?.isPasswordValid(password)!! && presenter?.isUsernameValid(username)!!) {
            presenter?.onLoginButtonClick(username, password)
            hideLoading()
        } else {
            hideLoading()
            showToastError(Constants.error1)
        }
    }

    override fun showUserCreated() {
        Toast.makeText(this, "Пользователь успешно создан!", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun onRegistrationClick() {
        showLoading()
        val usernameText = username.text.toString()
        val passwordText = password.text.toString()
        if (presenter?.isPasswordValid(passwordText)!! && presenter?.isUsernameValid(usernameText)!!) {
            presenter?.onRegistrationButtonClick(usernameText, passwordText)
            hideLoading()
        } else {
            hideLoading()
            showToastError(Constants.error1)
        }
        password.text.clear()
        username.text.clear()
    }

    override fun showToastError(error: String) {
        Toast.makeText(
            applicationContext, error,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun registrationSuccess() {
        Toast.makeText(
            applicationContext, "Registration success!",
            Toast.LENGTH_LONG
        ).show()
        goOnLoginView()
    }

    private fun goOnRegistrationView() {
        container1.visibility = View.GONE
        container2.visibility = View.VISIBLE
        hint_registration.visibility = View.VISIBLE
        registration_button.setOnClickListener {
            onRegistrationClick()
        }
    }

    private fun goOnLoginView() {
        registration.paintFlags = Paint.UNDERLINE_TEXT_FLAG //устанавливает подчеркивание на текст
        container1.visibility = View.VISIBLE
        container2.visibility = View.GONE
        hint_registration.visibility = View.GONE
        login_button.setOnClickListener {
            onLoginButtonClick()
        }
        registration.setOnClickListener {
            goOnRegistrationView()
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

}
