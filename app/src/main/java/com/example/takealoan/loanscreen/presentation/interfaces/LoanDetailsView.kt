package com.example.takealoan.loanscreen.presentation.interfaces

import com.example.takealoan.loanscreen.data.model.PostLoanModel

interface LoanDetailsView: BaseView {

    fun setFieldsOfLoan(loanModel: PostLoanModel)
}