package com.example.takealoan.loanscreen.presentation.interfaces

interface LoanDetailsPresenter {
    fun onDestroy()

    fun attachView(view: LoanDetailsView)

    fun getLoanDetails(id: Int)
}