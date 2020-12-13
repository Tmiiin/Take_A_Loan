package com.example.takealoan.loanscreen.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.takealoan.R
import com.example.takealoan.loanscreen.data.model.LoanStatus
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.di.LoanDetailsFactory
import com.example.takealoan.loanscreen.presentation.interfaces.LoanDetailsPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanDetailsView
import kotlinx.android.synthetic.main.loan_details.*
import java.text.SimpleDateFormat
import java.time.LocalDate

class ShowLoanDetailsFragment : Fragment(), LoanDetailsView {

    val TAG = "DetailsFragment"
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

    override fun setFieldsOfLoan(loanModel: PostLoanModel) {
        val name = "${loanModel.lastName} ${loanModel.firstName}"
        when (loanModel.state) {
            LoanStatus.APPROVED.toString() -> {
                imageView.setImageResource(R.drawable.baseline_check_circle_outline_black_18dp)
                context?.let {
                    LoanStatus.APPROVED.color(it).let {
                        linear_layout.setBackgroundColor(
                            it
                        )
                    }
                }
                status.text = LoanStatus.APPROVED.text()
            }
            LoanStatus.REGISTERED.toString() -> {
                imageView.setImageResource(R.drawable.baseline_schedule_black_18dp)
                context?.let {
                    LoanStatus.REGISTERED.color(it).let {
                        linear_layout.setBackgroundColor(
                            it
                        )
                    }
                }
                status.text = LoanStatus.REGISTERED.text()
            }
            LoanStatus.REJECTED.toString() -> {
                imageView.setImageResource(R.drawable.baseline_highlight_off_black_18dp)
                context?.let {
                    LoanStatus.REJECTED.color(it).let {
                        linear_layout.setBackgroundColor(
                            it
                        )
                    }
                }
                status.text = LoanStatus.REJECTED.text()
            }
        }
        customer_name.text = name
        val formattedDate = reformatDate(loanModel.date)
        loan_get_date.text = formattedDate
        val loanEndDate =
            "${getLastDate(loanModel.date, loanModel.period)} + ${formattedDate.split(" ")[1]}"
        loan_end_date.text = loanEndDate
        amount.text = loanModel.amount.toString()
    }

    //форматируем дату до приемлемого формата
    private fun reformatDate(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return formatter.format(parser.parse(date.split(".").first()))
    }

    //получаем срок, в который нужно отдать кредит
    private fun getLastDate(date: String, month: Int): String {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            var localDate: LocalDate = LocalDate.parse(date.split("T").first())
            localDate = localDate.plusMonths(month.toLong())
            val formatter = SimpleDateFormat("dd.MM.yyyy")
            val parser = SimpleDateFormat("yyyy-MM-dd")
            Log.i(TAG, localDate.toString())
            formatter.format(parser.parse(localDate.toString()))
        } else {
            "??"
        }
    }

}