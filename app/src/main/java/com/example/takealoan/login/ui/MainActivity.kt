package com.example.takealoan.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.takealoan.R


class MainActivity : AppCompatActivity() {

/*
    private var presenter: LoginPresenter? = null
    val TAG = "LoginView"
*/

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
  /*  override fun loginSuccess(token: String) {
        Preferences(this).setToken(token)
        Log.i(TAG, "Token is: $token")
        Toast.makeText(
            applicationContext, "Enter success!",
            Toast.LENGTH_LONG
        ).show()
        //?????? it should crash ??????
      //  login_button.findNavController().navigate(R.id.viewTransactionsAction)
       // val intent = Intent(this@MainActivity, LoanFragment::class.java)
       // startActivity(intent)
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

    private fun goOnLoginView() {
        registration.paintFlags = Paint.UNDERLINE_TEXT_FLAG //устанавливает подчеркивание на текст
        hint_registration.visibility = View.GONE
        login_button.setOnClickListener {
            onLoginButtonClick()
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
*/
}
