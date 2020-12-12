package com.example.takealoan.loanscreen.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.takealoan.R
import com.example.takealoan.loanscreen.data.model.ConditionsModel
import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.di.LoanPresenterFactory
import com.example.takealoan.loanscreen.presentation.interfaces.LoanPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanView
import kotlinx.android.synthetic.main.create_loan.*


class CreateLoanFragment : Fragment(),
    LoanView { //отвечает за экран создания loan

    private var presenter: LoanPresenter? = null
    override var conditionsModel: ConditionsModel = ConditionsModel()
    val TAG = "CreateLoanFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initPresenter()
        presenter?.getLoanCondition()
        return inflater.inflate(R.layout.create_loan, null)
    }

    fun setFields(curValue: Int) {
        amount.text = curValue.toString()
        val percentOfLoan = amount.text.toString().toFloat() * conditionsModel.percent / 100f
        loan_cost.text = (amount.text.toString().toInt() +
                percentOfLoan).toInt().toString()
        loan_percent.text = (percentOfLoan).toInt().toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //выставляет доступной для просмотра текущуу сумму займа
        seekbar.setOnSeekBarChangeListener { _, curValue ->
            setFields(curValue)
        }
        seekbar.curProcess = 0

        button.setOnClickListener { view ->
            val bundle = Bundle()
            bundle.putString("amount", amount.text.toString().toInt().toString())
            bundle.putString("percent", conditionsModel.percent.toString())
            bundle.putString("period", conditionsModel.period.toString())
            bundle.putString("maxAmount", conditionsModel.maxAmount.toString())
            val action = R.id.action_createLoanFragment_to_getLoanFragment
            Navigation.findNavController(view).navigate(action, bundle)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun showToastError(error: String) {
        Toast.makeText(
            context, error,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun initPresenter() {
        presenter = context?.let { LoanPresenterFactory.create(it) }
        presenter?.attachView(this)
    }

    override fun setMaxAmount(maxAmount: Int) {
        //  amount.hint = "Сумма меньше $maxAmount"
        seekbar.maxProcess = maxAmount
        max_amount.text = maxAmount.toString()
    }

    override fun setConditionHint(percent: Float, period: Int) {
        loan_percent_term_hint.text = loan_percent_term_hint.text.replace(
            "%xx".toRegex(),
            percent.toString()
        )
            .replace("%yy".toRegex(), period.toString())

    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

}
