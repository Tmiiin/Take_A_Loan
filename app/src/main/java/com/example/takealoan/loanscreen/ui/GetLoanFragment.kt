package com.example.takealoan.loanscreen.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.takealoan.R
import com.example.takealoan.loanscreen.data.model.ConditionsModel
import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.di.GetLoanPresenterFactory
import com.example.takealoan.loanscreen.di.LoanPresenterFactory
import com.example.takealoan.loanscreen.presentation.interfaces.BaseView
import com.example.takealoan.loanscreen.presentation.interfaces.GetLoanPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanView
import kotlinx.android.synthetic.main.create_loan.*
import kotlinx.android.synthetic.main.get_a_loan.*
import kotlinx.android.synthetic.main.get_a_loan.button
import kotlin.math.max


class GetLoanFragment() : Fragment(), BaseView {

    lateinit var amount: String
    lateinit var percent: String
    lateinit var period: String
    lateinit var maxAmount: String
    val TAG = "GetLoanFragment"

    private var presenter: GetLoanPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amount = arguments?.getString("amount").toString()
        percent = arguments?.getString("percent").toString()
        period = arguments?.getString("period").toString()
        maxAmount = arguments?.getString("maxAmount").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initPresenter()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.get_a_loan, container, false)
    }

    private fun initPresenter() {
        presenter = context?.let { GetLoanPresenterFactory.create(it) }
        presenter?.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            try {
                if (validateInput()) {
                    presenter?.onCreateButtonClick(
                        LoanModel(
                            amount = amount.toInt(),
                            firstName = first_name.text.toString(),
                            percent = percent.toFloat(),
                            period = period.toInt(),
                            maxAmount = maxAmount.toInt(),
                            lastName = last_name.text.toString()
                        )
                    )
                    showToastError("Займ успешно оформлен!")
                    //закрываем клавиатуру
                    context?.let { it1 -> hideKeyboardFrom(it1, view) }
                    //и переходим к общему списку займов
                    val action = R.id.action_getLoanFragment_to_loanListFragment
                    Navigation.findNavController(view).navigate(action)
                } else showToastError("Заполните все поля!")
            } catch (e: Exception) {
                showToastError("Вам нужно заполнить хотя бы одно поле!")
                Log.e(TAG, e.message.toString())
            }
        }
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showToastError(error: String) {
        Toast.makeText(
            context, error,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun validateInput(): Boolean =
        first_name.text.toString().isNotEmpty()
                && last_name.text.toString().isNotEmpty()
                && phone_number.text.toString().isNotEmpty()


/*    companion object {
     @JvmStatic
        fun newInstance(param1: String, param2: String) =
        *//*    GetLoanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*//*
    }*/
}