package com.example.takealoan.login.ui

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.takealoan.R
import com.example.takealoan.login.di.LoginPresenterFactory
import com.example.takealoan.login.presentation.LoginPresenter
import com.example.takealoan.login.presentation.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity(), LoginView {

    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initPresenter()
        goOnLoginView()
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
            showUnacceptableNamePassword()
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
            showUnacceptableNamePassword()
        }
        password.text.clear()
        username.text.clear()
    }

    override fun showUsernamePasswordError() {
        Toast.makeText(
            applicationContext, "error in username or password",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showAuthError() {
        Toast.makeText(
            applicationContext, "error in authorization",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showUnacceptableNamePassword() {
        Toast.makeText(
            applicationContext, "Unacceptable password or username",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun registrationSuccess() {
        Toast.makeText(
            applicationContext, "Registration success!",
            Toast.LENGTH_LONG
        ).show()
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
