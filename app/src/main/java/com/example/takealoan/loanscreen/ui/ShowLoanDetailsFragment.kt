package com.example.takealoan.loanscreen.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.takealoan.R
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.di.LoanDetailsFactory
import com.example.takealoan.loanscreen.presentation.interfaces.LoanDetailsPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanDetailsView
import kotlinx.android.synthetic.main.loan_details.*

class ShowLoanDetailsFragment: Fragment(), LoanDetailsView {

    private var presenter: LoanDetailsPresenter? = null
    var itemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemId = arguments?.getInt("id").toString().toInt()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initPresenter()
        presenter?.getLoanDetails(itemId)
        return inflater.inflate(R.layout.loan_details, null)
    }

    fun initPresenter() {
        presenter = context?.let { LoanDetailsFactory.create(it) }
        presenter?.attachView(this)
    }

    override fun setFieldsOfLoan(loanModel: PostLoanModel){
        val name: String = "${loanModel.lastName} ${loanModel.firstName}"
        status.text = "Заявка одобрена!))"
        imageView.setImageResource(R.drawable.baseline_schedule_black_18dp)
        linear_layout.setBackgroundColor(Color.RED);
        customer_name.text = name
        loan_get_date.text = loanModel.date
        loan_end_date.text = loanModel.date + "пока не посчитали"
        amount.text = loanModel.amount.toString()
    }

}