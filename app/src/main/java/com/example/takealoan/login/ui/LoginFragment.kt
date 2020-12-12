package com.example.takealoan.login.ui

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.takealoan.Constants
import com.example.takealoan.Preferences
import com.example.takealoan.R
import com.example.takealoan.login.di.LoginPresenterFactory
import com.example.takealoan.login.presentation.interfaces.LoginPresenter
import com.example.takealoan.login.ui.interfaces.LoginView
import kotlinx.android.synthetic.main.login.*

class LoginFragment : Fragment(), LoginView {

    private var presenter: LoginPresenter? = null
    val TAG = "LoginView"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeView()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initPresenter()
        return inflater.inflate(R.layout.login, null)
    }

    override fun loginSuccess(token: String) {
        context?.let { Preferences(it).setToken(token) }
        Log.i(TAG, "Token is: $token")
        Toast.makeText(
            context, "Enter success!",
            Toast.LENGTH_LONG
        ).show()

        context?.let { view?.let { it1 -> hideKeyboardFrom(it, it1) } }
        //?????? it should crash ??????
        val action = R.id.action_loginFragment_to_loanListFragment
        Navigation.findNavController(login_button).navigate(action)
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
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

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun showToastError(error: String) {
        Toast.makeText(
            context, error,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun initializeView() {
        registration.paintFlags = Paint.UNDERLINE_TEXT_FLAG //устанавливает подчеркивание на текст
        hint_registration.visibility = View.GONE
        login_button.setOnClickListener {
            onLoginButtonClick()
        }
        registration.setOnClickListener {
            val action = R.id.action_loginFragment_to_registrationFragment
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

}