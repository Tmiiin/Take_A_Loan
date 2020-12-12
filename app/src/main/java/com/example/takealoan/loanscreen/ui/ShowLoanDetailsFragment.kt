package com.example.takealoan.loanscreen.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.takealoan.R
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.di.LoanDetailsFactory
import com.example.takealoan.loanscreen.presentation.interfaces.LoanDetailsPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanDetailsView
import kotlinx.android.synthetic.main.loan_details.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

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
        when(loanModel.state){
            LoanStatus.APPROVED.toString() -> {
                imageView.setImageResource(R.drawable.baseline_check_circle_outline_black_18dp)
                val color = context?.let { ContextCompat.getColor(it, R.color.design_default_color_surface) }
                color?.let { linear_layout.setBackgroundColor(it) }
                status.text = LoanStatus.APPROVED.text()
            }
            LoanStatus.REGISTERED.toString() ->{
                imageView.setImageResource(R.drawable.baseline_schedule_black_18dp)
                val color = context?.let { ContextCompat.getColor(it, R.color.purple_500) }
                color?.let { linear_layout.setBackgroundColor(it) };
                status.text = LoanStatus.REGISTERED.text()
            }
            LoanStatus.REJECTED.toString() ->{
                imageView.setImageResource(R.drawable.baseline_highlight_off_black_18dp)
                val color = context?.let { ContextCompat.getColor(it, R.color.def_wheel_color) }
                color?.let { linear_layout.setBackgroundColor(it) };
                status.text = LoanStatus.REJECTED.text()
            }
        }
        customer_name.text = name
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
       val formattedDate: String = formatter.format(parser.parse(loanModel.date.split(".").first()))
        loan_get_date.text = formattedDate
        loan_end_date.text = formattedDate + "пока не посчитали"
        amount.text = loanModel.amount.toString()
    }

}