package com.example.takealoan.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.takealoan.Constants
import com.example.takealoan.R
import com.example.takealoan.login.di.LoginPresenterFactory
import com.example.takealoan.login.di.RegistrationPresenterFactory
import com.example.takealoan.login.presentation.interfaces.LoginPresenter
import com.example.takealoan.login.presentation.interfaces.RegistrationPresenter
import com.example.takealoan.login.ui.interfaces.LoginView
import com.example.takealoan.login.ui.interfaces.RegistrationView
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registration.*
import kotlinx.android.synthetic.main.registration.password
import kotlinx.android.synthetic.main.registration.username

class RegistrationFragment() : Fragment(), RegistrationView {

    private var presenter: RegistrationPresenter? = null
    val TAG = "RegistrationFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.registration, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initPresenter()
        registration_button.setOnClickListener {
            onRegistrationClick()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initPresenter() {
        presenter = RegistrationPresenterFactory.create()
        presenter?.attachView(this)
    }

    override fun showLoading() {
        registration_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        registration_loading.visibility = View.GONE
    }

    override fun showToastError(error: String) {
        Toast.makeText(
            context, error,
            Toast.LENGTH_LONG
        ).show()
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

    override fun showUserCreated() {
        Toast.makeText(
            context, "User created!",
            Toast.LENGTH_LONG
        ).show()
    }


    override fun registrationSuccess() {
        Toast.makeText(
            context, "Registration success!",
            Toast.LENGTH_LONG
        ).show()
        val action = R.id.action_registrationFragment_to_loginFragment
        this.view?.let { Navigation.findNavController(it).navigate(action) }
    }

}