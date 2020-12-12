package com.example.takealoan.loanscreen.presentation.interfaces

import com.example.takealoan.loanscreen.data.model.LoanModel

interface GetLoanPresenter {
    fun onDestroy()

    fun attachView(view: BaseView)

    fun onCreateButtonClick(loanModel: LoanModel)
}